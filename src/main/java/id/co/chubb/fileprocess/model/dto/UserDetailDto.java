package id.co.chubb.fileprocess.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class    UserDetailDto {
    private String samAccountName;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String distinguishedName;
    private String statusAccount;
    private String newPrepaidRole;
    private String branchCode;
}
