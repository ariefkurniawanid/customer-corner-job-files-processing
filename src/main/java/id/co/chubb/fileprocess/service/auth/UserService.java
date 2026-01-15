package id.co.chubb.fileprocess.service.auth;

import id.co.chubb.fileprocess.model.dto.UserDetailDto;
import id.co.chubb.fileprocess.model.dto.UserRoleDto;
import id.co.chubb.fileprocess.model.entity.NpsUser;
import id.co.chubb.fileprocess.model.entity.NpsUserRole;
import id.co.chubb.fileprocess.respository.NpsUserRepository;
import id.co.chubb.fileprocess.respository.NpsUserRoleRepository;
import id.co.chubb.fileprocess.util.ConstantUtils;
import id.co.chubb.fileprocess.util.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private NpsUserRepository npsUserRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private NpsUserRoleRepository npsUserRoleRepository;

    public UserRoleDto validate(UserDetailDto userDetailDto) {
        MessageUtils.setLogMessage("Validate LDAP Authentication with User : " + userDetailDto.getDistinguishedName(), logger);
        UserRoleDto userRoleDto = null;
        Optional<NpsUser> user = npsUserRepository.findByLogin(userDetailDto.getSamAccountName());
        String role = userDetailDto.getNewPrepaidRole();
        if (!user.isPresent()) {
            user = Optional.ofNullable(insertNewUser(userDetailDto));
        }
        Long userId = user.get().getId();
        userRoleDto = roleService.checkRole(role);
        userRoleDto.setUserId(user.get().getId());
        Optional<NpsUserRole> userRole = npsUserRoleRepository.findByUserId(userId);
        if (!userRole.isPresent()) {
            insertUserRole(userRoleDto);
        } else {
            MessageUtils.setLogMessage("Update Data User Role with Id : " + userId, logger);
            userRole.get().setDetailsRolesId(userRoleDto.getDetailsRolesId());
        }
        return userRoleDto;
    }

    @Transactional
    public NpsUser insertNewUser(UserDetailDto userDetailDto) {
        MessageUtils.setLogMessage("Insert New User Login", logger);
        NpsUser npsUser = new NpsUser();
        npsUser.setLogin(userDetailDto.getSamAccountName());
        npsUser.setFirstName(userDetailDto.getFirstName());
        npsUser.setLastName(userDetailDto.getLastName());
        npsUser.setActivated(Boolean.TRUE);
        npsUser.setEmail(userDetailDto.getEmail());
        npsUser.setCreatedBy(ConstantUtils.SYS);
        npsUser.setCreatedDate(LocalDateTime.now());
        npsUser.setLastModifiedBy(ConstantUtils.SYS);
        npsUser.setLastModifiedDate(LocalDateTime.now());
        npsUser.setStatus(Long.valueOf(2));
        npsUser.setActive(Boolean.TRUE);
        npsUser.setLocked(Boolean.FALSE);
        return npsUserRepository.save(npsUser);
    }

    @Transactional
    public void insertUserRole(UserRoleDto userRoleDto) {
        MessageUtils.setLogMessage("Insert New User Role", logger);
        NpsUserRole npsUserRole = new NpsUserRole();
        npsUserRole.setUserId(userRoleDto.getUserId());
        npsUserRole.setDetailsRolesId(userRoleDto.getDetailsRolesId());
        npsUserRoleRepository.save(npsUserRole);
    }
}
