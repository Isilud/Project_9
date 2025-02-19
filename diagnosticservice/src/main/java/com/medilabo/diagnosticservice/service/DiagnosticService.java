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

/**
 * Service qui gère la logique de diagnostic des patients en fonction de leurs notes médicales.
 * Ce service interagit avec un client pour obtenir les données de diagnostic et de patient,
 * puis analyse les informations pour déterminer un niveau de diagnostic basé sur des mots-clés présents
 * dans les notes médicales du patient.
 */
@Service
public class DiagnosticService {
    private final Logger logger = LoggerFactory.getLogger(DiagnosticService.class);

    private final GateServiceClient gateServiceClient;

    public DiagnosticService(GateServiceClient gateServiceClient) {
        this.gateServiceClient = gateServiceClient;
    }

    // Liste des mots-clés utilisés pour évaluer le diagnostic
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

    /**
     * Méthode pour obtenir le niveau de diagnostic d'un patient en fonction des notes médicales
     * et des informations personnelles du patient (âge et sexe).
     * 
     * @param patientId L'identifiant du patient dont le niveau de diagnostic est à déterminer.
     * @return Le niveau de diagnostic du patient basé sur l'analyse des mots-clés présents dans les notes médicales
     *         et des critères du patient (âge et sexe).
     */
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

        // Détermination du niveau de diagnostic basé sur les mots-clés trouvés
        return determineDiagnosticLevel(foundKeywords.size(), patient);
    }

    /**
     * Méthode qui détermine le niveau de diagnostic basé sur le nombre de mots-clés trouvés
     * et les informations du patient (âge et sexe).
     * 
     * @param keywordCount Le nombre de mots-clés trouvés dans les notes médicales du patient.
     * @param patient Le patient dont les informations sont utilisées pour évaluer le niveau de diagnostic.
     * @return Le niveau de diagnostic du patient en fonction de l'analyse des mots-clés et des critères personnels.
     */
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
