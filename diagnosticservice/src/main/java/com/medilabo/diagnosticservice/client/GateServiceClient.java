package com.medilabo.diagnosticservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilabo.diagnosticservice.model.Note;
import com.medilabo.diagnosticservice.model.Patient;

@FeignClient(name = "gateservice", url = "${gateservice.url}")
public interface GateServiceClient {

    @GetMapping("/notes/{patientId}")
    List<Note> getDiagnostics(@PathVariable("patientId") Integer patientId);

    @GetMapping("/patients/{patientId}")
    Patient getPatient(@PathVariable("patientId") Integer patientId);
}
