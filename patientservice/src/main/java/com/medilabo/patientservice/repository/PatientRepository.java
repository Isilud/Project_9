package com.medilabo.patientservice.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medilabo.patientservice.model.Patient;

import jakarta.annotation.PostConstruct;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @PostConstruct
    @Profile("dev")
    public default void insertTestData() {
        List<Patient> testData = Arrays.asList(
                new Patient("TestNone", "Test", "1966-12-31", "F", "1 Brookside St", "100-222-3333"),
                new Patient("TestBorderline", "Test", "1945-06-24", "M", "2 High St", "200-333-4444"),
                new Patient("TestInDanger", "Test", "2004-06-18", "M", "3 Club Road", "300-444-5555"),
                new Patient("TestEarlyOnset", "Test", "2002-06-28", "F", "4 Valley Dr", "400-555-6666"));
        saveAll(testData);
    }
}