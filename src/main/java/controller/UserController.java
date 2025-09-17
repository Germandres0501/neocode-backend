package com.neocode.backend.controller;

import com.neocode.backend.model.User;
import com.neocode.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya está registrado");
        }

        User nuevoUsuario = userService.save(user); // Aquí se encripta la contraseña
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}
