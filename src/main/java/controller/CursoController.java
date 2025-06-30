package com.neocode.backend.controller;

import com.neocode.backend.model.Curso;
import com.neocode.backend.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // Cambia si tu frontend usa otro puerto
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> obtenerCursos() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso cursoGuardado = cursoService.save(curso);
        return ResponseEntity.ok(cursoGuardado);
    }

    // Obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCursoPorId(@PathVariable String id) {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar curso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable String id) {
        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
