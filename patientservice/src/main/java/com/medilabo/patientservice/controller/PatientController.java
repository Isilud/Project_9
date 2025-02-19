package com.medilabo.patientservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.medilabo.patientservice.model.Patient;
import com.medilabo.patientservice.service.PatientService;

import jakarta.validation.Valid;

import java.util.List;

/**
 * Contrôleur pour la gestion des patients.
 * Fournit des endpoints pour ajouter, récupérer, mettre à jour et supprimer des
 * patients.
 */
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Endpoint pour ajouter un nouveau patient.
     * 
     * @param patient Patient à ajouter.
     * @return Le patient ajouté avec son identifiant généré.
     */
    @PostMapping
    public Patient addPatient(@Valid @RequestBody Patient patient) {
        logger.info("Save request for new patient");
        Patient registeredPatient = patientService.addPatient(patient);
        logger.info("Saved patient with id: {}", patient.getId());
        return registeredPatient;
    }

    /**
     * Endpoint pour récupérer tous les patients.
     *
     * @return Liste de tous les patients enregistrés.
     */
    @GetMapping
    public List<Patient> getAllPatients() {
        logger.info("Fetch request for all patients");
        return patientService.getAllPatients();
    }

    /**
     * Endpoint pour récupérer un patient par son identifiant.
     * 
     * @param id Identifiant du patient à récupérer.
     * @return Le patient correspondant à l'identifiant donné.
     */
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Integer id) {
        logger.info("Fetch request for patient with id: {}", id);
        Patient foundPatient = patientService.getPatientById(id);
        logger.info("Patient found with id: {}", id);
        return foundPatient;
    }

    /**
     * Endpoint pour mettre à jour un patient.
     * 
     * @param id      Identifiant du patient à mettre à jour.
     * @param patient Objet patient contenant les nouvelles informations.
     * @return Le patient mis à jour avec ses nouvelles informations.
     */
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Integer id, @Valid @RequestBody Patient patient) {
        logger.info("Update request for patient with id: {}", id);
        patient.setId(id); // Définit l'ID du patient avant la mise à jour
        Patient updatedPatient = patientService.updatePatient(patient);
        logger.info("Patient updated with id: {}", id);
        return updatedPatient;
    }

    /**
     * Endpoint pour supprimer un patient par son identifiant.
     * 
     * @param id Identifiant du patient à supprimer.
     */
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id) {
        logger.info("Delete request for patient with id: {}", id);
        patientService.deletePatient(id);
    }
}
