package com.medilabo.diagnosticservice.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.medilabo.diagnosticservice.client.GateServiceClient;
import com.medilabo.diagnosticservice.model.DiagnosticLevel;
import com.medilabo.diagnosticservice.model.Note;
import com.medilabo.diagnosticservice.model.Patient;
import com.medilabo.diagnosticservice.service.DiagnosticService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = "test")
public class DiagnosticServiceTest {

    @Mock
    private GateServiceClient gateServiceClient;

    @InjectMocks
    private DiagnosticService diagnosticService;

    private Patient malePatient;
    private Patient femalePatient;

    @BeforeEach
    void setUp() {
        diagnosticService = new DiagnosticService(gateServiceClient);
        malePatient = new Patient(LocalDate.now().minusYears(25), 'M');
        femalePatient = new Patient(LocalDate.now().minusYears(28), 'F');
    }

    @Test
    void testGetLevel_None() {
        when(gateServiceClient.getDiagnostics(1)).thenReturn(Collections.emptyList());
        when(gateServiceClient.getPatient(1)).thenReturn(malePatient);

        DiagnosticLevel level = diagnosticService.getLevel(1);
        assertEquals(DiagnosticLevel.NONE, level);
    }

    @Test
    void testGetLevel_Borderline() {
        List<Note> notes = Arrays.asList(new Note("Taille"), new Note("Poids"));
        Patient testPatient = new Patient(LocalDate.now().minusYears(36), 'M');
        when(gateServiceClient.getDiagnostics(1)).thenReturn(notes);
        when(gateServiceClient.getPatient(1)).thenReturn(testPatient);

        DiagnosticLevel level = diagnosticService.getLevel(1);
        assertEquals(DiagnosticLevel.BORDERLINE, level);
    }

    @Test
    void testGetLevel_Danger() {
        List<Note> notes = Arrays.asList(new Note("Taille"), new Note("Poids"), new Note("Fumeur"));
        when(gateServiceClient.getDiagnostics(1)).thenReturn(notes);
        when(gateServiceClient.getPatient(1)).thenReturn(malePatient);

        DiagnosticLevel level = diagnosticService.getLevel(1);
        assertEquals(DiagnosticLevel.DANGER, level);
    }

    @Test
    void testGetLevel_Early() {
        List<Note> notes = Arrays.asList(new Note("Taille"), new Note("Poids"), new Note("Fumeur"),
                new Note("Cholestérol"), new Note("Vertiges"));
        when(gateServiceClient.getDiagnostics(1)).thenReturn(notes);
        when(gateServiceClient.getPatient(1)).thenReturn(malePatient);

        DiagnosticLevel level = diagnosticService.getLevel(1);
        assertEquals(DiagnosticLevel.EARLY, level);
    }

    @Test
    void testGetLevel_Early_Female() {
        List<Note> notes = Arrays.asList(new Note("Taille"), new Note("Poids"), new Note("Fumeur"),
                new Note("Cholestérol"), new Note("Vertiges"), new Note("Rechute"), new Note("Réaction"));
        when(gateServiceClient.getDiagnostics(2)).thenReturn(notes);
        when(gateServiceClient.getPatient(2)).thenReturn(femalePatient);

        DiagnosticLevel level = diagnosticService.getLevel(2);
        assertEquals(DiagnosticLevel.EARLY, level);
    }
}
