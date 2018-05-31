package org.vahad.opdracht.rest.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.vahad.opdracht.domein.PatientFilter;
import org.vahad.opdracht.dto.PatientDto;
import org.vahad.opdracht.rest.api.model.Patient;
import org.vahad.opdracht.service.PatientService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link PatientController}
 *
 * @author Daniël Bosmans
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private PatientService service;
    
    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void after() {
        Mockito.verifyNoMoreInteractions(service);
    }
    
    @Test
    public void registerNewPatientSuccessTest() throws Exception {
        // Given
        when(service.registerPatient(Mockito.any(PatientDto.class))).thenReturn(true);
        
        // When
        mvc.perform(MockMvcRequestBuilders.post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson(createNewPatient())))
                .andExpect(status().isCreated());
        
        // Then
        verify(service, times(1)).registerPatient(Mockito.any(PatientDto.class));
    }
    
    @Test
    public void registerNewPatientConflictTest() throws Exception {
        // Given
        when(service.registerPatient(Mockito.any(PatientDto.class))).thenReturn(false);
        
        // When
        mvc.perform(MockMvcRequestBuilders.post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson(createNewPatient())))
                .andExpect(status().isConflict());
        
        // Then
        verify(service, times(1)).registerPatient(Mockito.any(PatientDto.class));
    }
    
    @Test
    public void getPatientsSuccessTest() throws Exception{
        // Given
        final List<PatientDto> daniel = Collections.singletonList(new PatientDto.Builder().setFirstName("daniel").build());
        when(service.searchPatients(Mockito.any(PatientFilter.class))).thenReturn(daniel);
    
        // When
        mvc.perform(get("/patients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    
        // Then
        verify(service, times(1)).searchPatients(Mockito.any(PatientFilter.class));
    }
    
    @Test
    public void getPatientsSuccessNoContentTest() throws Exception{
        // Given
        when(service.searchPatients(Mockito.any(PatientFilter.class))).thenReturn(Collections.emptyList());
        
        // When
        mvc.perform(get("/patients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        
        // Then
        verify(service, times(1)).searchPatients(Mockito.any(PatientFilter.class));
    }
    
    private String createUserInJson(Patient patient) {
        return "{ \"dateOfBirth\": \"" + "1989-01-01" + "\", " +
                "\"firstname\":\"" + patient.getFirstname() + "\"," +
                "\"patientId\":\"" + patient.getPatientId() + "\"," +
                "\"isInsured\":\"" + patient.getIsInsured() + "\"," +
                "\"lastname\":\"" + patient.getLastname() + "\"}";
    }
    
    private Patient createNewPatient(){
        Patient patientModel = new Patient();
        patientModel.setPatientId("f431bdac-3e5b-446a-9940-eaj007c37419");
        patientModel.isInsured(true);
        patientModel.setDateOfBirth(LocalDate.of(1989, Month.MARCH, 3));
        patientModel.setFirstname("danie1");
        patientModel.setLastname("bosmans");
        return patientModel;
    }
}