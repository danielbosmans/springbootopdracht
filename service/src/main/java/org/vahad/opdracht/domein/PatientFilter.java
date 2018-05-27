package org.vahad.opdracht.domein;

import java.io.Serializable;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Data holder for filter criteria to search for patient object in database
 *
 * @author Daniël Bosmans
 */
@ParametersAreNonnullByDefault
public class PatientFilter implements Serializable {
    
    private final String patientId;
    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    
    private PatientFilter(final Builder builder) {
        this.patientId = builder.patientId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
    }
    
    @Nullable
    public String getPatientId() {
        return patientId;
    }
    
    @Nullable
    public String getFirstName() {
        return firstName;
    }
    
    @Nullable
    public String getLastName() {
        return lastName;
    }
    
    @Nullable
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    /**
     * Builder to build {@link PatientFilter} object
     */
    public static class Builder {
        private String patientId;
        private String firstName;
        private String lastName;
        private String dateOfBirth;
    
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
    
        public Builder setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
    
        public PatientFilter build() {
            return new PatientFilter(this);
        }
    }
}
