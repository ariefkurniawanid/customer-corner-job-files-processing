package id.co.chubb.fileprocess.controller;

import id.co.chubb.fileprocess.model.request.LoginRequest;
import id.co.chubb.fileprocess.model.response.GenericResponse;
import id.co.chubb.fileprocess.service.auth.AuthService;
import id.co.chubb.fileprocess.util.ConstantUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        GenericResponse response = authService.validateUserLogin(req);
        if (!ConstantUtils.SUCCESS_CODE.equals(response.getResponseCode())) {
            response.setResponseCode(response.getResponseCode());
            response.setResponseMessage(response.getResponseMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
