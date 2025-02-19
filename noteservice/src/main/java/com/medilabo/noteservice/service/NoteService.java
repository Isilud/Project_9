package com.medilabo.noteservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medilabo.noteservice.model.Note;
import com.medilabo.noteservice.repository.NoteRepository;

/**
 * Service pour la gestion des notes médicales des patients.
 * Fournit des opérations pour récupérer, ajouter, mettre à jour et supprimer
 * des notes.
 */
@Service
public class NoteService {
    private final Logger logger = LoggerFactory.getLogger(NoteService.class);
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Récupère toutes les notes associées à un patient donné.
     *
     * @param patientId Identifiant du patient.
     * @return Liste des notes du patient.
     */
    public List<Note> getNotesByPatientId(int patientId) {
        List<Note> notes = noteRepository.findByPatientId(patientId);
        logger.debug("Found {} notes for patient ID: {}", notes.size(), patientId);
        return notes;
    }

    /**
     * Ajoute une nouvelle note médicale.
     *
     * @param note Note à ajouter.
     * @return La note sauvegardée.
     */
    public Note addNote(Note note) {
        Note savedNote = noteRepository.save(note);
        logger.debug("Note saved: {}", savedNote);
        return savedNote;
    }

    /**
     * Met à jour une note existante.
     *
     * @param note Note avec les modifications à appliquer.
     * @return La note mise à jour.
     */
    public Note updateNote(Note note) {
        Note updatedNote = noteRepository.save(note);
        logger.debug("Note updated: {}", updatedNote);
        return updatedNote;
    }

    /**
     * Supprime une note par son identifiant.
     *
     * @param id Identifiant unique de la note.
     */
    public void deleteNoteById(String id) {
        noteRepository.deleteById(id);
        logger.debug("Note deleted with ID: {}", id);
    }
}