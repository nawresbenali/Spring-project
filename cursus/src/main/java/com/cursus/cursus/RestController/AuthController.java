package com.cursus.cursus.RestController;

import com.cursus.cursus.Entity.User;
import com.cursus.cursus.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role) {

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Load the user details
        UserDetails userDetails = userService.loadUserByUsername(username);

        // Cast userDetails to User to access roles
        if (userDetails instanceof User) {
            User user = (User) userDetails;

            // Check if the user has the required role
            boolean hasRole = user.getRoles().stream()
                    .anyMatch(r -> r.getName().equals(role));
            if (!hasRole) {
                return ResponseEntity.badRequest().body("Invalid role for user: " + username);
            }

            return ResponseEntity.ok("User authenticated: " + user.getUsername() + " with role: " + role);
        }

        return ResponseEntity.badRequest().body("Unable to retrieve user role");
    }
}
