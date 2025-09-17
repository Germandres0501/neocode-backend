package com.neocode.backend.controller;

import com.neocode.backend.dto.LoginRequest;
import com.neocode.backend.model.User;
import com.neocode.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }

        User user = userOptional.get();

        boolean passwordValida = userService.validatePassword(user, loginRequest.getPassword());

        if (!passwordValida) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        return ResponseEntity.ok("Inicio de sesión exitoso");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            // Verificar si el email ya existe
            Optional<User> existingUser = userService.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                return ResponseEntity.badRequest().body("El email ya está registrado");
            }

            // Establecer fecha de registro
            user.setFechaRegistro(LocalDateTime.now());
            user.setActivo(true);

            // Guardar usuario (el service debe encriptar la contraseña)
            User savedUser = userService.save(user);

            return ResponseEntity.ok("Usuario creado exitosamente: " + savedUser.getEmail());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear usuario: " + e.getMessage());
        }
    }
}