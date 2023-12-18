package com.salesianos.triana.dam.jwtwithrefreshtoken.note.repository;

import com.salesianos.triana.dam.jwtwithrefreshtoken.note.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByAuthor(String author);
}