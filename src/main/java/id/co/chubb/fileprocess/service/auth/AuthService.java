package id.co.chubb.fileprocess.service.auth;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import id.co.chubb.fileprocess.config.JwtUtils;
import id.co.chubb.fileprocess.model.dto.UserDetailDto;
import id.co.chubb.fileprocess.model.dto.UserRoleDto;
import id.co.chubb.fileprocess.model.entity.*;
import id.co.chubb.fileprocess.model.request.LoginRequest;
import id.co.chubb.fileprocess.model.response.GenericResponse;
import id.co.chubb.fileprocess.model.response.LoginResponse;
import id.co.chubb.fileprocess.respository.NpsUserRepository;
import id.co.chubb.fileprocess.util.ConstantUtils;
import id.co.chubb.fileprocess.util.MessageUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    public static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final LdapTemplate ldapTemplate;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private NpsUserRepository npsUserRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuditTrailService auditTrailService;
    @Autowired
    private MenuService menuService;
    @Value("${ldap.baseDn}")
    private String baseDn;
    @Value("${ldap.attribute.accountId}")
    private String accountId;
    @Value("${ldap.attribute.cn:}")
    private String cn;
    @Value("${ldap.attribute.distinguishedName:}")
    private String distinguishedName;
    @Value("${ldap.attribute.statusAcc:}")
    private String statusAccount;
    @Value("${ldap.attribute.role:}")
    private String role;
    @Value("${ldap.attribute.firstName:}")
    private String firstName;
    @Value("${ldap.attribute.lastName:}")
    private String lastName;
    @Value("${ldap.attribute.name:}")
    private String fullName;
    @Value("${ldap.attribute.mail:}")
    private String mail;
    @Value("${ldap.attribute.branchCode:}")
    private String branchCode;

    public AuthService(LdapTemplate ldapTemplate, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.ldapTemplate = ldapTemplate;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public UserDetailDto getUserDetail(String username, NpsUser userAdmin, Boolean isSuperAdmin, String role) {
        UserDetailDto userDetailDto = new UserDetailDto();
        if (isSuperAdmin) {
            Long id = userAdmin.getId();
            userDetailDto.setSamAccountName(userAdmin.getLogin());
            userDetailDto.setNewPrepaidRole(role);
            userDetailDto.setBranchCode("000");
        } else {
            userDetailDto = ldapTemplate.search(
                    LdapQueryBuilder.query()
                            .base(baseDn)
                            .filter("(" + accountId + "=" + username + ")"),
                    (AttributesMapper<UserDetailDto>) attrs -> {
                        String cn = getAttr(attrs, this.cn);
                        String distinguishedName = getAttr(attrs, this.distinguishedName);
                        String statusAccount = getAttr(attrs, this.statusAccount);
                        String roles = getAttr(attrs, this.role);
                        String firstName = getAttr(attrs, this.firstName);
                        String lastName = getAttr(attrs, this.lastName);
                        String mail = getAttr(attrs, this.mail);
                        String fullName = getAttr(attrs, this.fullName);
                        String branchCode = getAttr(attrs, this.branchCode);

                        return new UserDetailDto(
                                username,
                                firstName,
                                lastName,
                                fullName,
                                mail,
                                distinguishedName,
                                statusAccount,
                                roles,
                                branchCode
                        );
                    }
            ).stream().findFirst().orElse(null);
        }
        return userDetailDto;
    }

    private String getAttr(Attributes attrs, String name) throws NamingException {
        return attrs.get(name) != null ? attrs.get(name).get().toString() : null;
    }

    private List<String> getMulti(Attributes attrs, String name) throws NamingException {
        List<String> list = new ArrayList<>();
        if (attrs.get(name) != null) {
            NamingEnumeration<?> en = attrs.get(name).getAll();
            while (en.hasMore()) {
                list.add(en.next().toString());
            }
        }
        return list;
    }

    @Transactional
    public GenericResponse validateUserLogin(LoginRequest loginRequest) {
        GenericResponse genericResponse = new GenericResponse();
        LoginResponse loginResponse = new LoginResponse();
        Boolean isSuperAdmin = Boolean.FALSE;
        Boolean successLogin = Boolean.FALSE;
        UserDetailDto userDetailDto = new UserDetailDto();
        if (ConstantUtils.SUPERADMIN.equals(loginRequest.getUsername())) {
            Optional<NpsUser> user = npsUserRepository.findByLogin(loginRequest.getUsername());
            if (user.isPresent()) {
                MessageUtils.setLogMessage("USER SUPERADMIN", logger);
                String encodedPassword = user.get().getPasswordHash();
                if (passwordEncoder.matches(loginRequest.getPassword(), encodedPassword)) {
                    genericResponse.setResponseCode(ConstantUtils.SUCCESS_CODE);
                    genericResponse.setResponseMessage(ConstantUtils.SUCCESS_MESSAGE);
                    isSuperAdmin = Boolean.TRUE;
                    // setelah ini tentukan UserDetails, Role dan Menu
                    UserRoleDto role = gettingRolesParent(loginRequest.getUsername());
                    String jwt = jwtUtils.generateToken(loginRequest.getUsername());
                    userDetailDto = getUserDetail(loginRequest.getUsername(), user.get(), isSuperAdmin, role.getRoleAuth());
                    loginResponse.setToken(jwt);
                    String roleUser = role.getRoleAuth() != null ? role.getRoleAuth() : ConstantUtils.NO_ROLES;
                    loginResponse.setRole(roleUser);
                    loginResponse.setUserDetail(userDetailDto);
                    genericResponse.setData(loginResponse);
                    successLogin = Boolean.TRUE;
                } else {
                    genericResponse.setResponseCode(ConstantUtils.AUTH_FAILED_CODE);
                    genericResponse.setResponseMessage(ConstantUtils.AUTH_FAILED_MESSAGE);
                }
            } else {
                genericResponse.setResponseCode(ConstantUtils.NOTFOUND_CODE);
                genericResponse.setResponseMessage(ConstantUtils.NOT_FOUND_MESSAGE);
            }
        } else {
            try {
                MessageUtils.setLogMessage("NOT USER SUPERADMIN, AUTHENTICATE WITH LDAP", logger);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
                authenticationManager.authenticate(authenticationToken);
                userDetailDto = getUserDetail(loginRequest.getUsername(), null, isSuperAdmin, null);
                UserRoleDto userRoleDto = userService.validate(userDetailDto);
                String jwt = jwtUtils.generateToken(loginRequest.getUsername());
                loginResponse.setToken(jwt);
                loginResponse.setRole(userRoleDto.getRoleAuth());
                loginResponse.setUserDetail(userDetailDto);
                genericResponse.setResponseCode(ConstantUtils.SUCCESS_CODE);
                genericResponse.setResponseMessage(ConstantUtils.SUCCESS_MESSAGE);
                genericResponse.setData(loginResponse);
                successLogin = Boolean.TRUE;
            } catch (Exception e) {
                genericResponse.setResponseCode(ConstantUtils.AUTH_FAILED_CODE);
                genericResponse.setResponseMessage(ConstantUtils.AUTH_FAILED_MESSAGE);
            }
        }
        if (successLogin) {
            auditTrailService.logAudit(userDetailDto);
        }
        return genericResponse;
    }

    @Transactional
    public UserRoleDto gettingRolesParent(String username) {
        BooleanBuilder builder = new BooleanBuilder();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QNpsUser qNpsUser = QNpsUser.npsUser;
        QNpsUserRole qNpsUserRole = QNpsUserRole.npsUserRole;
        QNpsDetailsRole qNpsDetailsRole = QNpsDetailsRole.npsDetailsRole;
        QNpsRole qNpsRole = QNpsRole.npsRole;
        builder.and(qNpsUser.login.eq(username));

        UserRoleDto role = jpaQueryFactory.select(Projections.fields(UserRoleDto.class,
                        qNpsRole.id,
                        qNpsRole.name.as("roleAuth")
                ))
                .from(qNpsUser)
                .join(qNpsUserRole).on(qNpsUserRole.userId.eq(qNpsUser.id))
                .join(qNpsDetailsRole).on(qNpsDetailsRole.id.eq(qNpsUserRole.detailsRolesId))
                .join(qNpsRole).on(qNpsRole.id.eq(qNpsDetailsRole.rolesId))
                .where(builder)
                .fetchOne();
        return role;
    }
}
