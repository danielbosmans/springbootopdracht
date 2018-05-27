package org.vahad.opdracht.rest.controller;

import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vahad.opdracht.domein.PatientFilter;
import org.vahad.opdracht.rest.api.PatientsApi;
import org.vahad.opdracht.rest.api.model.Patient;
import org.vahad.opdracht.service.PatientService;

/**
 * TODO (Daniël Bosmans): Add file description here
 *
 * @author Daniël Bosmans
 */
@RestController
public class PatientController implements PatientsApi {
   
    @Autowired
    private PatientService patientService;
    
    @Override
    public ResponseEntity<List<Patient>> getPatients(
            @ApiParam(value = "Unique identification of the patient") @RequestParam(value = "patientId", required = false) String patientId,
            @ApiParam(value = "Name of the patient") @RequestParam(value = "firstname", required = false) String firstname,
            @ApiParam(value = "Firstname of the patient") @RequestParam(value = "lastname", required = false) String lastname,
            @ApiParam(value = "Search for patients that are born after given date") @RequestParam(value = "fromDateOfBirth", required = false) String fromDateOfBirth) {
        PatientFilter filter = new PatientFilter.Builder()
                .setPatientId(patientId)
                .setFirstName(firstname)
                .setLastName(lastname)
                .setDateOfBirth(fromDateOfBirth)
                .build();
        
        List<Patient> patientList = patientService.searchPatients(filter).stream()
                .map(ControllerMapper::mapPatientDtoToModel)
                .collect(Collectors.toList());
        if(patientList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patientList,HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Void> registerPatient(@RequestBody Patient patient) {
        if(patientService.registerPatient(ControllerMapper.mapPatientModelToDto(patient))){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
