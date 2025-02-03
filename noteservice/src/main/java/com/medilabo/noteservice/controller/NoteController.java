package com.medilabo.noteservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.noteservice.model.Note;
import com.medilabo.noteservice.service.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController {

    Logger logger = LoggerFactory.getLogger(NoteController.class);

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{patientId}")
    public List<Note> getNotesByPatientId(@PathVariable String patientId) {
        logger.info("Fetch request for patient with id : ", patientId);
        List<Note> notes = noteService.getNotesByPatientId(patientId);
        logger.info("Found ", notes.size(), " notes for patient with id ", patientId);
        return notes;
    }

    @PostMapping
    public Note addNote(@RequestBody Note note) {
        logger.info("Save request for new note");
        Note savedNote = noteService.addNote(note);
        logger.info("Note saved with id ", savedNote.getId());
        return savedNote;
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note note) {
        logger.info("Update request for note with id : ", id);
        Note updatedNote = noteService.updateNote(note);
        logger.info("Updated note with id ", updatedNote.getId());
        return updatedNote;
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable String id) {
        logger.info("Deletion request for note with id ", id);
        noteService.deleteNoteById(id);
    }
}
