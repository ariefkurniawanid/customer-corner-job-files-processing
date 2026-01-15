package id.co.chubb.fileprocess.model.dto;

import lombok.Data;

@Data
public class UserRoleDto {
    private Long userId;
    private Long detailsRolesId;
    private String roleLdap;
    private String roleAuth;
}
