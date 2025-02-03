package com.medilabo.noteservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medilabo.noteservice.model.Note;
import com.medilabo.noteservice.repository.NoteRepository;

@Service
public class NoteService {
    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotesByPatientId(int noteId) {
        List<Note> notes = noteRepository.findByPatientId(noteId);
        logger.debug("Found ", notes.size(), " notes");
        return notes;
    }

    public Note addNote(Note note) {
        Note savedNote = noteRepository.save(note);
        logger.debug("Note saved : ", note);
        return savedNote;
    }

    public Note updateNote(Note note) {
        Note updatedNote = noteRepository.save(note);
        logger.debug("Note updated : ", updatedNote);
        return updatedNote;
    }

    public void deleteNoteById(String id) {
        noteRepository.deleteById(id);
        logger.debug("Note deleted with : ", id);
    }
}
