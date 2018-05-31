package org.vahad.opdracht.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.vahad.opdracht.dao.PatientRepository;
import org.vahad.opdracht.dao.PatientSpecification;
import org.vahad.opdracht.domein.Patient;
import org.vahad.opdracht.domein.PatientFilter;
import org.vahad.opdracht.dto.PatientDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link PatientServiceImpl}
 *
 * @author Daniël Bosmans
 */

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceImplTest {
    
    @Mock
    private PatientRepository patientRepository;
    
    @InjectMocks
    private PatientServiceImpl patientService;
    
    private static final String FIRSTNAME = "daniel";
    private static final String LASTNAME = "bosmans";
    private static final String PATIENT_ID = "f431bdac-3e5b-446a-9940-eaj007c37419";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(1989, Month.MARCH, 3);
    
    @After
    public void after() {
        Mockito.verifyNoMoreInteractions(patientRepository);
    }
    
    @Test
    public void searchPatientsTest() {
        // Given
        ArgumentCaptor<PatientSpecification> specificationArgumentCaptor = ArgumentCaptor.forClass(PatientSpecification.class);
        when(patientRepository.findAll(specificationArgumentCaptor.capture())).thenReturn(Collections.singletonList(createNewPatient()));
        
        // When
        final List<PatientDto> resultList = patientService.searchPatients(new PatientFilter.Builder().setFirstName(FIRSTNAME).build());
        
        // Then
        verify(patientRepository, times(1)).findAll(specificationArgumentCaptor.capture());
        Assert.assertFalse(resultList.isEmpty());
        Assert.assertEquals(1, resultList.size());
        
        PatientDto actualResult = resultList.get(0);
        Assert.assertEquals(FIRSTNAME, actualResult.getFirstName());
        Assert.assertEquals(LASTNAME, actualResult.getLastName());
        Assert.assertEquals(PATIENT_ID, actualResult.getPatientId());
        Assert.assertEquals(DATE_OF_BIRTH, actualResult.getDateOfBirth());
        Assert.assertEquals(true, actualResult.getInsured());
        
        Assert.assertNotNull(specificationArgumentCaptor.getValue());
    }
    
    @Test
    public void searchPatientsEmptyTest(){
        // Given
        ArgumentCaptor<PatientSpecification> specificationArgumentCaptor = ArgumentCaptor.forClass(PatientSpecification.class);
        when(patientRepository.findAll(any(PatientSpecification.class))).thenReturn(Collections.emptyList());
    
        // When
        final List<PatientDto> resultList = patientService.searchPatients(new PatientFilter.Builder().build());
        
        // Then
        verify(patientRepository, times(1)).findAll(specificationArgumentCaptor.capture());
        Assert.assertTrue(resultList.isEmpty());
    }
    
    @Test
    public void registerPatientSuccessTest(){
        // Given
        ArgumentCaptor<Patient> patientDtoArgumentCaptor = ArgumentCaptor.forClass(Patient.class);
        ArgumentCaptor<String> patientIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(patientRepository.findPatientByPatientId(patientIdArgumentCaptor.capture())).thenReturn(null);
        when(patientRepository.save(patientDtoArgumentCaptor.capture())).thenReturn(createNewPatient());
        
        // When
        boolean result = patientService.registerPatient(createNewPatientDto());
        
        // Then
        verify(patientRepository, times(1)).findPatientByPatientId(patientIdArgumentCaptor.capture());
        verify(patientRepository, times(1)).save(patientDtoArgumentCaptor.capture());
        
        Assert.assertTrue(result);
        Assert.assertEquals(PATIENT_ID, patientIdArgumentCaptor.getValue());
        Assert.assertNotNull(patientDtoArgumentCaptor.getValue());
        Assert.assertEquals(PATIENT_ID, patientDtoArgumentCaptor.getValue().getPatientId());
    }
    
    @Test
    public void registerPatientFailure(){
        // Given
        ArgumentCaptor<String> patientIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(patientRepository.findPatientByPatientId(patientIdArgumentCaptor.capture())).thenReturn(createNewPatient());
        
        // When
        boolean result = patientService.registerPatient(createNewPatientDto());
        
        // Then
        verify(patientRepository, times(1)).findPatientByPatientId(patientIdArgumentCaptor.capture());
        verify(patientRepository, times(0)).save(createNewPatient()); //Never called
        
        Assert.assertFalse(result);
    }
    
    private PatientDto createNewPatientDto() {
        return new PatientDto.Builder()
                .setPatientId(PATIENT_ID)
                .isInsured(true)
                .setDateOfBirth(DATE_OF_BIRTH)
                .setFirstName(FIRSTNAME)
                .setLastName(LASTNAME)
                .build();
    }
    
    private Patient createNewPatient() {
        return new Patient.Builder()
                .setPatientId(PATIENT_ID)
                .isInsured(true)
                .setDateOfBirth(DATE_OF_BIRTH)
                .setFirstName(FIRSTNAME)
                .setLastName(LASTNAME)
                .build();
    }
}