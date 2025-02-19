package com.medilabo.diagnosticservice.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.medilabo.diagnosticservice.model.Note;
import com.medilabo.diagnosticservice.model.Patient;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@WireMockTest(httpPort = 8080)
public class DiagnosticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${gateservice.url}")
    private String gateServiceUrl;

    @BeforeEach
    void presetup() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @BeforeEach
    void setup() {
        WireMock.configureFor("localhost", 8080);
    }

    @Test
    void shouldReturnDiagnosticLevelForPatient() throws Exception {
        int patientId = 1;
        List<Note> notes = List.of(new Note("Patient has high cholesterol"));
        Patient patient = new Patient(LocalDate.now(), 'M');

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/notes/" + patientId))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(notes))));

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/patients/" + patientId))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(patient))));

        mockMvc.perform(get("/diagnostic/" + patientId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
