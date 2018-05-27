package org.vahad.opdracht.dto;

import java.time.LocalDate;
import javax.annotation.concurrent.Immutable;

/**
 * TODO (Daniël Bosmans): Add file description here
 *
 * @author Daniël Bosmans
 */
@Immutable
public final class PatientDto {
    
    private long id;
    private final String patientId;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final Boolean isInsured;
    
    private PatientDto(Builder builder) {
        this.patientId = builder.patientId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
        this.isInsured = builder.isInsured;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public Boolean getInsured() {
        return isInsured;
    }
    
    public long getId() {
        return id;
    }
    
    /**
     * Builder voor {@link PatientDto} dto object
     */
    public static class Builder {
        private String patientId;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private Boolean isInsured;
        
        public Builder setPatientId(String patientId) {
            this.patientId = patientId;
            return this;
        }
        
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
    
        public Builder isInsured(Boolean isInsured) {
            this.isInsured = isInsured;
            return this;
        }
        
        public PatientDto build() {
            return new PatientDto(this);
        }
    }
}
