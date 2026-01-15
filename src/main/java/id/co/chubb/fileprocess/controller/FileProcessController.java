package id.co.chubb.fileprocess.controller;

import id.co.chubb.fileprocess.model.request.LoginRequest;
import id.co.chubb.fileprocess.model.response.GenericResponse;
import id.co.chubb.fileprocess.service.fileprocess.FileProcessService;
import id.co.chubb.fileprocess.util.ConstantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileProcessController {

    @Autowired
    private FileProcessService fileProcessService;

    @GetMapping("/register-fund")
    public ResponseEntity<?> registerFilesFund() throws IOException {
        fileProcessService.processFileFund();
        return ResponseEntity.ok("Successfully registered files");
    }
}
