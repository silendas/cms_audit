package com.cms.audit.service;

import java.util.Optional;

import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

import com.cms.audit.config.jwt.JwtService;
import com.cms.audit.dto.SigninDTO;
import com.cms.audit.dto.SignupDTO;
import com.cms.audit.dto.response.AuthenticationResponse;
import com.cms.audit.models.entities.users.Role;
import com.cms.audit.models.entities.users.User;
import com.cms.audit.models.repositories.UserRepository;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
        private final UserRepository userRepository;

        private final PasswordEncoder passwordEncoder;

        private final JwtService jwtService;

        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse signup(SignupDTO signupDTO) {
                try {
                        var user = User.builder()
                                        .email(signupDTO.getEmail())
                                        .username(signupDTO.getUsername())
                                        .password(passwordEncoder.encode(signupDTO.getPassword()))
                                        .profile(signupDTO.getProfile())
                                        .role(Role.USER)
                                        .build();

                        User response = userRepository.save(user);

                        return AuthenticationResponse.builder()
                                        .data(response)
                                        .status(HttpStatus.OK)
                                        .build();
                } catch (RequestRejectedException e) {
                        return AuthenticationResponse.builder()
                                        .err("Something went wrong : " + e.getMessage())
                                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                                        .build();
                } catch (DataException e) {
                        return AuthenticationResponse.builder()
                                        .err("Something went wrong : " + e.getMessage())
                                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                                        .build();
                } catch (JwtException e) {
                        return AuthenticationResponse.builder()
                                        .err("Something went wrong : " + e.getMessage())
                                        .status(HttpStatus.FORBIDDEN)
                                        .build();
                } catch (Exception e) {
                        return AuthenticationResponse.builder()
                                        .err("Something went wrong : " + e.getMessage())
                                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .build();
                }
        }

        public AuthenticationResponse signin(SigninDTO signinDTO) {
                try {
                        // validate username or email
                        Optional<User> response = userRepository.findOneUsersByEmailOrUsername(signinDTO.getUsername(), signinDTO.getUsername());
                        if(!response.isPresent()){
                                return AuthenticationResponse.builder()
                                .err("Wrong username or email")
                                .status(HttpStatus.OK)
                                .build();
                        };

                        // valdite auth manager user
                        authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        response.get().getEmail(),
                                                        signinDTO.getPassword()));

                        User user = userRepository.findByEmail(response.get().getEmail()).orElseThrow();

                        var jwtToken = jwtService.generateToken(user);

                        return AuthenticationResponse.builder()
                                        .token(jwtToken)
                                        .data(response.orElseThrow())
                                        .status(HttpStatus.OK)
                                        .build();
                } catch (DataException e) {
                        return AuthenticationResponse.builder()
                                        .err("Something went wrong : " + e.getMessage())
                                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                                        .build();
                } catch (AuthenticationException e) {
                        return AuthenticationResponse.builder()
                                        .err("Something went wrong : " + e.getMessage())
                                        .status(HttpStatus.FORBIDDEN)
                                        .build();
                } catch (JwtException e) {
                        return AuthenticationResponse.builder()
                                        .err("Something went wrong : " + e.getMessage())
                                        .status(HttpStatus.FORBIDDEN)
                                        .build();
                } catch (Exception e) {
                        return AuthenticationResponse.builder()
                                        .err("Something went wrong : " + e.getMessage())
                                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .build();
                }

        }
}
