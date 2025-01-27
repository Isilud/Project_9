package com.medilabo.patientservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medilabo.patientservice.exception.PatientNotFoundException;
import com.medilabo.patientservice.model.Patient;
import com.medilabo.patientservice.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        logger.debug("Patient saved : ", patient);
        return savedPatient;
    }

    public List<Patient> getAllPatients() {
        List<Patient> listPatient = patientRepository.findAll();
        logger.debug("Found ", listPatient.size(), " patients.");
        return listPatient;
    }

    public Patient getPatientById(Integer id) throws PatientNotFoundException {
        Optional<Patient> foundPatient = patientRepository.findById(id);
        if (!foundPatient.isPresent()) {
            throw new PatientNotFoundException(id);
        }
        logger.debug("Patient found : ", foundPatient.get());
        return foundPatient.get();
    }

    public Patient updatePatient(Patient patient) {
        Patient updatedPatient = patientRepository.save(patient);
        logger.debug("Patient updated : ", updatedPatient);
        return updatedPatient;
    }

    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
        logger.debug("Patient deleted with : ", id);
    }
}