package com.medilabo.noteservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medilabo.noteservice.model.Note;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByPatientId(String patientId);
}