package com.medilabo.patientservice.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.medilabo.patientservice.model.Patient;
import com.medilabo.patientservice.repository.PatientRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("dev") // Cette configuration est active uniquement en mode dev
public class PatientDataConfig {

    private final PatientRepository patientRepository;

    public PatientDataConfig(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostConstruct
    public void insertTestData() {
        List<Patient> testData = Arrays.asList(
                new Patient("Test", "TestNone", "1966-12-31", "F", "1 Brookside St", "100-222-3333"),
                new Patient("Test", "TestBorderline", "1945-06-24", "M", "2 High St", "200-333-4444"),
                new Patient("Test", "TestInDanger", "2004-06-18", "M", "3 Club Road", "300-444-5555"),
                new Patient("Test", "TestEarlyOnset", "2002-06-28", "F", "4 Valley Dr", "400-555-6666"));

        patientRepository.saveAll(testData);
    }
}