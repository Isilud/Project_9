package com.medilabo.noteservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.medilabo.noteservice.model.Note;
import com.medilabo.noteservice.service.NoteService;

/**
 * Contrôleur REST pour la gestion des notes médicales des patients.
 * Fournit des points d'entrée pour récupérer, ajouter, mettre à jour et
 * supprimer des notes.
 */
@RestController
@RequestMapping("/notes")
public class NoteController {

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Récupère toutes les notes associées à un patient.
     *
     * @param patientId Identifiant du patient pour lequel récupérer les notes.
     * @return Liste des notes du patient.
     */
    @GetMapping("/{patientId}")
    public List<Note> getNotesByPatientId(@PathVariable Integer patientId) {
        logger.info("Fetch request for patient with id: {}", patientId);
        List<Note> notes = noteService.getNotesByPatientId(patientId);
        logger.info("Found {} notes for patient with id {}", notes.size(), patientId);
        return notes;
    }

    /**
     * Ajoute une nouvelle note pour un patient.
     *
     * @param note Objet contenant les informations de la nouvelle note.
     * @return La note ajoutée avec ses informations sauvegardées.
     */
    @PostMapping
    public Note addNote(@RequestBody Note note) {
        logger.info("Save request for new note");
        Note savedNote = noteService.addNote(note);
        logger.info("Note saved with id: {}", savedNote.getId());
        return savedNote;
    }

    /**
     * Met à jour une note existante.
     *
     * @param id   Identifiant de la note à mettre à jour.
     * @param note Objet contenant les nouvelles informations de la note.
     * @return La note mise à jour.
     */
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note note) {
        logger.info("Update request for note with id: {}", id);
        note.setId(id); // Assure que l'ID de la note est correctement mis à jour
        Note updatedNote = noteService.updateNote(note);
        logger.info("Updated note with id: {}", updatedNote.getId());
        return updatedNote;
    }

    /**
     * Supprime une note en fonction de son identifiant.
     *
     * @param id Identifiant de la note à supprimer.
     */
    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable String id) {
        logger.info("Deletion request for note with id: {}", id);
        noteService.deleteNoteById(id);
    }
}
