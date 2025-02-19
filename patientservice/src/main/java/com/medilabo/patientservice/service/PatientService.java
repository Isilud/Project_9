package com.medilabo.patientservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medilabo.patientservice.exception.PatientNotFoundException;
import com.medilabo.patientservice.model.Patient;
import com.medilabo.patientservice.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des patients.
 * Permet d'effectuer des opérations telles que l'ajout, la récupération, la
 * mise à jour et la suppression de patients.
 */
@Service
public class PatientService {

    private final Logger logger = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Ajoute un nouveau patient.
     *
     * @param patient Objet contenant les informations du patient à ajouter.
     * @return Le patient sauvegardé.
     */
    public Patient addPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        logger.debug("Patient saved: {}", patient);
        return savedPatient;
    }

    /**
     * Récupère tous les patients.
     *
     * @return Liste de tous les patients présents dans la base de données.
     */
    public List<Patient> getAllPatients() {
        List<Patient> listPatient = patientRepository.findAll();
        logger.debug("Found {} patients.", listPatient.size());
        return listPatient;
    }

    /**
     * Récupère un patient en fonction de son identifiant.
     *
     * @param id Identifiant du patient à récupérer.
     * @return Le patient correspondant à l'identifiant.
     * @throws PatientNotFoundException Si le patient n'est pas trouvé dans la base
     *                                  de données.
     */
    public Patient getPatientById(Integer id) throws PatientNotFoundException {
        Optional<Patient> foundPatient = patientRepository.findById(id);
        if (!foundPatient.isPresent()) {
            throw new PatientNotFoundException(id);
        }
        logger.debug("Patient found: {}", foundPatient.get());
        return foundPatient.get();
    }

    /**
     * Met à jour les informations d'un patient.
     *
     * @param patient Patient contenant les nouvelles informations à mettre à jour.
     * @return Le patient mis à jour.
     */
    public Patient updatePatient(Patient patient) {
        Patient updatedPatient = patientRepository.save(patient);
        logger.debug("Patient updated: {}", updatedPatient);
        return updatedPatient;
    }

    /**
     * Supprime un patient en fonction de son identifiant.
     *
     * @param id Identifiant du patient à supprimer.
     */
    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
        logger.debug("Patient deleted with id: {}", id);
    }
}
