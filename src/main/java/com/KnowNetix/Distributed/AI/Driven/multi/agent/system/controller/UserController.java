package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.ERole;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Role;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.payload.request.LoginRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.payload.request.SignupRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.payload.response.JwtResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.payload.response.MessageResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.RoleRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.jwt.JwtUtils;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @PostConstruct
    public void createRoles(){

        List<Role> roles = new ArrayList<>();
        Arrays.stream(ERole.values()).forEach(userRole -> {
            if (roleRepository.findByName(userRole).isEmpty()) {
                Role role = Role.builder()
                        .name(userRole)
                        .build();
                roles.add(role);
            }
        });
        roleRepository.saveAll(roles);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUser(),
                userDetails.getTelephoneNum(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getTelephoneNumber(),
                signUpRequest.getLevelOfEducation(),
                signUpRequest.getInterest());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "inst":
                        Role modRole = roleRepository.findByName(ERole.ROLE_INSTRUCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/admin/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> assignUserAsAdmin(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = user.get();
        user1.getRoles().add(new Role(3L, ERole.ROLE_ADMIN));
        userRepository.save(user1);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/instructor/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> assignUserAsInstructor(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = user.get();
        user1.getRoles().add(new Role(2L, ERole.ROLE_INSTRUCTOR));
        userRepository.save(user1);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/admin/remove/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> resetUserDefaultRole(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = user.get();
        user1.getRoles().clear();
        user1.getRoles().add(new Role(1L, ERole.ROLE_USER));
        userRepository.save(user1);
        return ResponseEntity.noContent().build();
    }
}