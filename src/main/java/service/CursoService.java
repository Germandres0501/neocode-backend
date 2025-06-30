package com.neocode.backend.service;

import com.neocode.backend.model.Curso;
import com.neocode.backend.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(String id) {
        return cursoRepository.findById(id);
    }

    public void deleteById(String id) {
        cursoRepository.deleteById(id);
    }
}
