package com.neocode.backend.controller;

import com.neocode.backend.dto.LoginRequest;
import com.neocode.backend.model.User;
import com.neocode.backend.service.UserService;
import com.neocode.backend.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ✅ Registrar nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }
        return ResponseEntity.ok(userService.save(user));
    }

    // ✅ Iniciar sesión y generar token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return userService.findByEmail(loginRequest.getEmail()).map(user -> {
            if (userService.validatePassword(user, loginRequest.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "user", user
                ));
            } else {
                return ResponseEntity.status(401).body("Contraseña incorrecta");
            }
        }).orElse(ResponseEntity.status(404).body("Usuario no encontrado"));
    }

    // ✅ Consultar usuario por email
    @GetMapping("/by-email")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Listar todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
