package org.vahad.opdracht.rest.controller;

import org.vahad.opdracht.dto.PatientDto;
import org.vahad.opdracht.rest.api.model.Patient;

/**
 * TODO (Daniël Bosmans): Add file description here
 *
 * @author Daniël Bosmans
 */
final class ControllerMapper {
    
    /**
     * Do not instantiate this class
     */
    private ControllerMapper() {
    }
    
    static Patient mapPatientDtoToModel(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setPatientId(patientDto.getPatientId());
        patient.setFirstname(patientDto.getFirstName());
        patient.setLastname(patientDto.getLastName());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setIsInsured(patientDto.getInsured());
        return patient;
    }
    
    static PatientDto mapPatientModelToDto(Patient patient){
        return new PatientDto.Builder()
                .setPatientId(patient.getPatientId())
                .setDateOfBirth(patient.getDateOfBirth())
                .setFirstName(patient.getFirstname())
                .setLastName(patient.getLastname())
                .isInsured(patient.getIsInsured())
                .build();
    }
}
