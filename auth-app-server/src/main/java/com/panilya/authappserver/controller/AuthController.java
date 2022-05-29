package com.panilya.authappserver.controller;

import com.panilya.authappserver.dto.LoginDTO;
import com.panilya.authappserver.dto.SignupDTO;
import com.panilya.authappserver.model.User;
import com.panilya.authappserver.repository.RoleRepository;
import com.panilya.authappserver.repository.UserRepository;
import com.panilya.authappserver.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        authenticationManager.authenticate(authenticationToken);
        return ResponseEntity.ok(jwtTokenProvider.generateToken(loginRequest.getUsername()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new IllegalStateException("User already exists");
        }

        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        User user = new User(signupRequest.getUsername(), encodedPassword);
        user.setUserRoles(Collections.singletonList(roleRepository.getById(1L)));
        userRepository.save(user);
        return ResponseEntity.ok("User successfully created");
    }

    @GetMapping("/protected-resources")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("I'm fine");
    }
}
