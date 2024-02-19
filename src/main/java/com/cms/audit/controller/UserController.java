package com.cms.audit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.audit.dto.UserDTO;
import com.cms.audit.dto.response.ResponseHandler;
import com.cms.audit.dto.response.UserResponse;
import com.cms.audit.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<Object> getAll() {
        UserResponse response = userService.findAllUser();
        if (response.getErr() != null) {
            return ResponseHandler.responseErrorGenerator(response.getErr(), response.getStatus());
        }
        return ResponseHandler.responseEntityGenerator("Success..", response.getStatus(), response.getDataList(),
                null);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getOneById(@PathVariable("id") Long id) {
        UserResponse response = userService.findOneUser(id);
        if (response.getErr() != null) {
            return ResponseHandler.responseErrorGenerator(response.getErr(), response.getStatus());
        }
        return ResponseHandler.responseEntityGenerator("Success..", response.getStatus(), response.getDataOptional(),null);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@ModelAttribute UserDTO userDTO) {
        UserResponse response = userService.createUser(userDTO);
        if (response.getErr() != null) {
            return ResponseHandler.responseErrorGenerator(response.getErr(), response.getStatus());
        }
        return ResponseHandler.responseEntityGenerator("Success..", response.getStatus(), response.getDataOptional(),null);
    }

}
