package com.medilabo.patientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medilabo.patientservice.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}