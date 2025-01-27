package com.medilabo.patientservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.medilabo.patientservice.model.Patient;
import com.medilabo.patientservice.service.PatientService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @PostMapping
    public Patient addPatient(@Valid @RequestBody Patient patient) {
        logger.info("Save request for new patient");
        Patient registeredPatient = patientService.addPatient(patient);
        logger.info("Saved patient with id : ", patient.getId());
        return registeredPatient;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        logger.info("Fetch request for all patients");
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Integer id) {
        logger.info("Fetch request for patient with id : ", id);
        Patient foundPatient = patientService.getPatientById(id);
        logger.info("Patient found : ", id);
        return foundPatient;
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Integer id, @Valid @RequestBody Patient patient) {
        logger.info("Update request for patient with id : ", id);
        patient.setId(id);
        Patient updatedPatient = patientService.updatePatient(patient);
        logger.info("Patient updated with id : ", id);
        return updatedPatient;
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id) {
        logger.info("Delete request for patient with id : ", id);
        patientService.deletePatient(id);
    }
}