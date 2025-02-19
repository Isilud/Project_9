package com.medilabo.diagnosticservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.diagnosticservice.model.DiagnosticLevel;
import com.medilabo.diagnosticservice.service.DiagnosticService;

/**
 * Contrôleur qui gère les requêtes HTTP relatives aux diagnostics des patients.
 * Ce contrôleur permet de récupérer le niveau de diagnostic d'un patient en fonction de son identifiant.
 * Les données sont fournies par le service `DiagnosticService`.
 */
@RestController
@RequestMapping("/diagnostic")
public class DiagnosticController {

    // Création d'un logger pour la classe DiagnosticController
    Logger logger = LoggerFactory.getLogger(DiagnosticController.class);

    private final DiagnosticService diagnosticService;

    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    /**
     * Méthode qui récupère le niveau de diagnostic d'un patient en fonction de son identifiant.
     * Cette méthode est exposée par la route HTTP GET /diagnostic/{patientId}.
     * 
     * @param patientId L'identifiant du patient pour lequel le diagnostic est demandé.
     * @return Le niveau de diagnostic du patient (de type {@link DiagnosticLevel}).
     */
    @GetMapping("/{patientId}")
    public DiagnosticLevel getDiagnosticForPatientId(@PathVariable Integer patientId) {
        logger.info("Fetch request for patient with id : ", patientId);
        // Appel du service pour récupérer le niveau de diagnostic du patient
        DiagnosticLevel level = diagnosticService.getLevel(patientId);
        logger.info("Danger level ", level.toString(), " for patient with id ", patientId);
        // Retour du niveau de diagnostic
        return level;
    }
}

