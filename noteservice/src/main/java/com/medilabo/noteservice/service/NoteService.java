package com.medilabo.noteservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medilabo.noteservice.model.Note;
import com.medilabo.noteservice.repository.NoteRepository;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotesByPatientId(String noteId) {
        return noteRepository.findByPatientId(noteId);
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNoteById(String id) {
        noteRepository.deleteById(id);
    }
}
