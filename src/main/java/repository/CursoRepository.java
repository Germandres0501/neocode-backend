package com.neocode.backend.repository;

import com.neocode.backend.model.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends MongoRepository<Curso, String> {
    // Aquí puedes agregar métodos personalizados si lo necesitas
}
