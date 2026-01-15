package id.co.chubb.fileprocess.service.auth;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import id.co.chubb.fileprocess.model.dto.UserRoleDto;
import id.co.chubb.fileprocess.model.entity.QNpsDetailsRole;
import id.co.chubb.fileprocess.model.entity.QNpsRole;
import id.co.chubb.fileprocess.respository.NpsDetailsRoleRepository;
import id.co.chubb.fileprocess.util.ConstantUtils;
import id.co.chubb.fileprocess.util.MessageUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    public static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    @Autowired
    private NpsDetailsRoleRepository npsDetailsRoleRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public UserRoleDto checkRole(String role) {
        MessageUtils.setLogMessage("Checking Roles with Role Name : " + role, logger);
        UserRoleDto userRoleDto = gettingRole(role);
        if (userRoleDto == null) {
            userRoleDto = gettingRole(ConstantUtils.NO_PRIVILEGE);
        }
        return userRoleDto;
    }

    @Transactional
    public UserRoleDto gettingRole(String role) {
        BooleanBuilder builder = new BooleanBuilder();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QNpsDetailsRole qNpsDetailsRole = QNpsDetailsRole.npsDetailsRole;
        QNpsRole qNpsRole = QNpsRole.npsRole;
        builder.and(qNpsDetailsRole.detailsRolesName.eq(role));

        return jpaQueryFactory.select(Projections.fields(UserRoleDto.class,
                        qNpsDetailsRole.id.as("detailsRolesId"),
                        qNpsDetailsRole.detailsRolesName.as("roleLdap"),
                        qNpsRole.name.as("roleAuth")
                ))
                .from(qNpsDetailsRole)
                .join(qNpsRole).on(qNpsRole.id.eq(qNpsDetailsRole.rolesId))
                .where(builder)
                .fetchOne();
    }
}
