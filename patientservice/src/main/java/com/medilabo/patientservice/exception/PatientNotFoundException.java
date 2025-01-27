package com.medilabo.patientservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientNotFoundException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(PatientNotFoundException.class);

    public PatientNotFoundException(Integer id) {
        super("Not found : Patient with id : " + id);
        logger.info("No patient with id : ", id);
    }
}
