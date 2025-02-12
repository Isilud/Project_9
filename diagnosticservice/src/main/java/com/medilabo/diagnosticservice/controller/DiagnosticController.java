package com.medilabo.diagnosticservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.diagnosticservice.model.DiagnosticLevel;
import com.medilabo.diagnosticservice.service.DiagnosticService;

@RestController
@RequestMapping("/diagnostic")
public class DiagnosticController {

    Logger logger = LoggerFactory.getLogger(DiagnosticController.class);

    private final DiagnosticService diagnosticService;

    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    @GetMapping("/{patientId}")
    public DiagnosticLevel getDiagnosticForPatientId(@PathVariable Integer patientId) {
        logger.info("Fetch request for patient with id : ", patientId);
        DiagnosticLevel level = diagnosticService.getLevel(patientId);
        logger.info("Danger level ", level.toString(), " for patient with id ", patientId);
        return level;
    }
}
