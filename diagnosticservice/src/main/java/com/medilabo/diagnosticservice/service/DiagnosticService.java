package com.medilabo.diagnosticservice.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medilabo.diagnosticservice.client.GateServiceClient;
import com.medilabo.diagnosticservice.model.DiagnosticLevel;
import com.medilabo.diagnosticservice.model.Note;
import com.medilabo.diagnosticservice.model.Patient;

@Service
public class DiagnosticService {
    private final Logger logger = LoggerFactory.getLogger(DiagnosticService.class);

    private final GateServiceClient gateServiceClient;

    public DiagnosticService(GateServiceClient gateServiceClient) {
        this.gateServiceClient = gateServiceClient;
    }

    static private List<String> keywords = Arrays.asList(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Fumeuse",
            "Anormal",
            "Cholestérol",
            "Vertiges",
            "Rechute",
            "Réaction",
            "Anticorps");

    public DiagnosticLevel getLevel(Integer patientId) {
        List<Note> notes = gateServiceClient.getDiagnostics(patientId);
        Patient patient = gateServiceClient.getPatient(patientId);
        logger.debug("Found " + notes.size() + " notes for patient " + patientId);
        Set<String> foundKeywords = new HashSet<>();
        for (String keyword : keywords) {
            for (Note note : notes) {
                String text = note.getText().toLowerCase();
                if (text.contains(keyword.toLowerCase())) {
                    logger.debug("Found " + keyword);
                    foundKeywords.add(keyword);
                    break;
                }
            }
        }
        return determineDiagnosticLevel(foundKeywords.size(), patient);
    }

    private DiagnosticLevel determineDiagnosticLevel(int keywordCount, Patient patient) {
        int age = patient.getAge();
        boolean isMale = patient.getGenre() == 'M';
        logger.debug("Patient is male :" + isMale);

        if ((isMale && age < 30 && keywordCount >= 5) ||
                (!isMale && age < 30 && keywordCount >= 7) ||
                (age > 30 && keywordCount >= 8)) {
            return DiagnosticLevel.EARLY;
        }

        if ((isMale && age < 30 && keywordCount >= 3) ||
                (!isMale && age < 30 && keywordCount >= 4) ||
                (age > 30 && keywordCount >= 6 && keywordCount <= 7)) {
            return DiagnosticLevel.DANGER;
        }

        if (keywordCount >= 2 && keywordCount <= 5 && age > 30) {
            return DiagnosticLevel.BORDERLINE;
        }

        if (keywordCount == 0) {
            return DiagnosticLevel.NONE;
        }

        return DiagnosticLevel.NONE;
    }
}
