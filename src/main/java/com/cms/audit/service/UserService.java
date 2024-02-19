package com.cms.audit.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cms.audit.dto.UserDTO;
import com.cms.audit.dto.response.UserResponse;
import com.cms.audit.models.entities.users.User;
import com.cms.audit.models.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserResponse findAllUser() {
        try {
            List<User> response = userRepository.findAll();

            return UserResponse.builder()
                    .dataList(response)
                    .status(HttpStatus.OK)
                    .build();
        } catch (DataException e) {
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .build();
        } catch (Exception e) {
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    public UserResponse findOneUser(Long id) {
        try {
            Optional<User> response = userRepository.findById(id);
            if(!response.isPresent()){
                return UserResponse.builder()
                    .err("Data not found !")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            }

            return UserResponse.builder()
                    .dataOptional(response)
                    .status(HttpStatus.OK)
                    .build();
        } catch (DataException e) {
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .build();
        } catch (Exception e) {
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    public UserResponse createUser(UserDTO userDTO) {
        try {
            var user = User.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(userDTO.getRole())
                    .profile(userDTO.getProfile())
                    .build();

            User response = userRepository.save(user);

            return UserResponse.builder()
                    .dataUser(response)
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (DataException e) {
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .build();
        } catch (Exception e) {
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    public UserResponse editUser(UserDTO userDTO) {
        try {
            var user = User.builder()
                    .userId(userDTO.getId())
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(userDTO.getRole())
                    .build();

            User response = userRepository.save(user);

            return UserResponse.builder()
                    .dataUser(response)
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (DataException e) {
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .build();
        } catch (Exception e) {
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    public UserResponse deleteUser(Long id){
        try{
            userRepository.deleteById(id);
            return UserResponse.builder()
                    .status(HttpStatus.OK)
                    .build();
        } catch (DataException e){
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        } catch (Exception e){
            return UserResponse.builder()
                    .err("Something went wrong : " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
