package com.cms.audit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.audit.dto.SigninDTO;
import com.cms.audit.dto.SignupDTO;
import com.cms.audit.dto.response.AuthenticationResponse;
import com.cms.audit.dto.response.ResponseHandler;
import com.cms.audit.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@ModelAttribute SignupDTO signupDTO) {
        AuthenticationResponse response = authService.signup(signupDTO);
        if (response.getErr() != null) {
            return ResponseHandler.responseErrorGenerator(response.getErr(), response.getStatus());
        }
        return ResponseHandler.responseEntityGenerator("Register success..", response.getStatus(), response.getData(),
                response.getToken());
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@ModelAttribute SigninDTO signinDTO) {
        AuthenticationResponse response = authService.signin(signinDTO);
        if (response.getErr()  != null) {
            return ResponseHandler.responseErrorGenerator(response.getErr(), response.getStatus());
        }
        return ResponseHandler.responseEntityGenerator("Login success..", response.getStatus(), response.getData(),
                response.getToken());
    }
}
