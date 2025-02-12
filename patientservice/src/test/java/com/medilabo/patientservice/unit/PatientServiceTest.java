package com.medilabo.patientservice.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medilabo.patientservice.exception.PatientNotFoundException;
import com.medilabo.patientservice.model.Patient;
import com.medilabo.patientservice.repository.PatientRepository;
import com.medilabo.patientservice.service.PatientService;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    public PatientService patientService;

    @BeforeEach
    public void setup() {
        patientService = new PatientService(patientRepository);
    }

    @Test
    public void testAddPatient() {
        // Given
        Patient patient = new Patient("John", "Doe", LocalDate.of(1, 1, 1), 'H', "123", "0123456789");

        // When
        when(patientRepository.save(patient)).thenReturn(patient);

        // Then
        Patient result = patientService.addPatient(patient);
        assertEquals("John", result.getPrenom());
        assertEquals("Doe", result.getNom());
        verify(patientRepository).save(patient);
    }

    @Test
    public void testGetAllPatients() {
        // Given
        Patient patient1 = new Patient("John", "Doe", LocalDate.of(1, 1, 1), 'H', "123", "0123456789");

        Patient patient2 = new Patient("Jane", "Doe",
                LocalDate.of(2, 2, 2), 'F', "456", "9876543210");

        // When
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        // Then
        List<Patient> result = patientService.getAllPatients();
        assertEquals(2, result.size());
        assertEquals("Doe", result.get(0).getNom());
        assertEquals("Jane", result.get(1).getPrenom());
        verify(patientRepository).findAll();
    }

    @Test
    public void testGetPatientById_Found() {
        // Given
        Patient patient = new Patient("John", "Doe", LocalDate.of(1, 1, 1), 'H', "123", "0123456789");

        // When
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));

        // Then
        Patient result = patientService.getPatientById(1);
        assertEquals("Doe", result.getNom());
        verify(patientRepository, times(1)).findById(1);
    }

    @Test
    public void testGetPatientById_NotFound() {
        // When
        when(patientRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(1));
    }

    @Test
    public void testUpdatePatient() {
        // Given
        Patient patient = new Patient("John", "Doe", LocalDate.of(1, 1, 1), 'H', "123", "0123456789");

        // When
        when(patientRepository.save(patient)).thenReturn(patient);

        // Then
        Patient result = patientService.updatePatient(patient);
        assertNotNull(result);
        assertEquals("123", result.getAdressePostale());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testDeletePatient() {
        // Given
        Integer id = 1;

        // When
        doNothing().when(patientRepository).deleteById(id);

        // Then
        patientService.deletePatient(id);
        verify(patientRepository, times(1)).deleteById(id);
    }
}