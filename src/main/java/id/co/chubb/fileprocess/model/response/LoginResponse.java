package id.co.chubb.fileprocess.model.response;

import id.co.chubb.fileprocess.model.dto.MenuDto;
import id.co.chubb.fileprocess.model.dto.UserDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String role;
    private UserDetailDto userDetail;
    private List<MenuDto> menu;
}
