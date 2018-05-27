package org.vahad.opdracht.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.vahad.opdracht.domein.Patient;

/**
 * @author Daniël Bosmans
 */
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {
    
    /**
     * Find one patient by its unique patientid
     *
     * @param patientId UUID
     * @return {@link Patient} matching patientId
     */
    Patient findPatientByPatientId(String patientId);
}
