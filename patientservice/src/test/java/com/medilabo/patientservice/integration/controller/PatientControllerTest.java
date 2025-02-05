package com.medilabo.patientservice.integration.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.patientservice.model.Patient;
import com.medilabo.patientservice.repository.PatientRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = "test")
public class PatientControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private PatientRepository patientRepository;

        public Patient patient1;
        public Patient patient2;

        @BeforeEach
        public void setup() {
                patientRepository.deleteAll();
                patient1 = patientRepository
                                .save(new Patient("John", "Doe", LocalDate.of(1, 1, 1), 'H', "123", "0123456789"));
                patient2 = patientRepository.save(new Patient("Jane", "Doe",
                                LocalDate.of(2, 2, 2), 'F', "456", "9876543210"));
                System.out.println(patientRepository.findAll());
        }

        @Test
        public void testAddPatient() throws Exception {
                Patient patient = new Patient("New", "Patient", LocalDate.of(1, 1, 1), 'H', "123", "0123456789");
                mockMvc.perform(post("/patients")
                                .content(objectMapper.writeValueAsString(patient))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.prenom").value("New"))
                                .andExpect(jsonPath("$.nom").value("Patient"));
        }

        @Test
        public void testGetAllPatients() throws Exception {
                mockMvc.perform(get("/patients"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(2)));
        }

        @Test
        public void testGetPatientById() throws Exception {
                mockMvc.perform(get("/patients/" + patient2.getId()))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.prenom").value("Jane"))
                                .andExpect(jsonPath("$.nom").value("Doe"));
        }

        @Test
        public void testGetPatientByIdNotFound() throws Exception {
                mockMvc.perform(get("/patients/0"))
                                .andExpect(status().isNotFound());
        }

        @Test
        public void testUpdatePatient() throws Exception {
                Patient updatedPatient = new Patient(patient1);
                updatedPatient.setPrenom("Joseph");
                mockMvc.perform(put("/patients/" + patient1.getId())
                                .content(objectMapper.writeValueAsString(
                                                updatedPatient))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.prenom").value("Joseph"));
        }

        @Test
        public void testDeletePatient() throws Exception {

                mockMvc.perform(delete("/patients/" + patient1.getId()))
                                .andExpect(status().isOk());

                mockMvc.perform(get("/patients/" + patient1.getId()))
                                .andExpect(status().isNotFound());
        }
}
