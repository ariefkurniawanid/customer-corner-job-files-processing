package id.co.chubb.fileprocess.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
