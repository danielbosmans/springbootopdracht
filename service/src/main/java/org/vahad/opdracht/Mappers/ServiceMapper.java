package org.vahad.opdracht.Mappers;

import org.vahad.opdracht.domein.Patient;
import org.vahad.opdracht.dto.PatientDto;

/**
 * @author Daniël Bosmans
 */
public final class ServiceMapper {
    
    /**
     * Do not instantiate
     */
    private ServiceMapper(){
    }
    
    public static PatientDto mapPatientDomeinToDto(final Patient patient) {
        return new PatientDto.Builder()
                .setPatientId(patient.getPatientId())
                .setFirstName(patient.getFirstname())
                .setLastName(patient.getLastname())
                .setDateOfBirth(patient.getDateOfBirth())
                .isInsured(patient.getInsured())
                .build();
    }
    
    public static Patient mapPatientDtoToDomein(final PatientDto patientDto) {
        return new Patient.Builder()
                .setPatientId(patientDto.getPatientId())
                .setFirstName(patientDto.getFirstName())
                .setLastName(patientDto.getLastName())
                .setDateOfBirth(patientDto.getDateOfBirth())
                .isInsured(patientDto.getInsured())
                .build();
    }
}
