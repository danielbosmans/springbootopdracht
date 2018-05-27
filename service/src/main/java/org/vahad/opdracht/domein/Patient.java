package org.vahad.opdracht.domein;

import java.time.LocalDate;
import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data holder for patient entity object
 *
 * @author Daniël Bosmans
 */
@Entity
@Table(name = "patient")
public final class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String patientId;
    private String firstname;
    private String lastname;
    private Boolean insured;
    private LocalDate dateOfBirth;
    
    public static final String PATIENT_ID_COLUMN = "patientId";
    public static final String FIRSTNAME_COLUMN = "firstname";
    public static final String LASTNAME_COLUMN = "lastname";
    public static final String DATE_OF_BIRTH_COLUMN = "dateOfBirth";
    public static final String INSURED_COLUMN = "insured";
    
    private Patient(@NotNull final Builder builder) {
        this.patientId = builder.patientId;
        this.firstname = builder.firstName;
        this.lastname = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
        this.insured = builder.insured;
    }
    
    /**
     * Required private no args constructor
     */
    private Patient() {
    }
    
    @NotNull(message="PatientId must be specified.")
    @Pattern(regexp="[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}", message = "PatientId must be a uuid")
    public String getPatientId() {
        return patientId;
    }
    
    @Size(max=30, message = "firstname is longer then 30 characters")
    public String getFirstname() {
        return firstname;
    }
    
    @Size(max=30, message = "lastname is longer then 30 characters")
    public String getLastname() {
        return lastname;
    }
    
    @Past(message = "dateOfBirth cannot be in the future")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public long getId() {
        return id;
    }
    
    @Nonnull
    public Boolean getInsured() {
        return insured;
    }
    
    /**
     * Builder voor {@link Patient} object
     */
    public static class Builder {
        private String patientId;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private Boolean insured;
    
        public Builder setPatientId(final String patientId) {
            this.patientId = patientId;
            return this;
        }
    
        public Builder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }
    
        public Builder setLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }
    
        public Builder setDateOfBirth(final LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
    
        public Builder isInsured(final Boolean isInsured) {
            this.insured = isInsured;
            return this;
        }
        public Patient build() {
            return new Patient(this);
        }
    }
}
