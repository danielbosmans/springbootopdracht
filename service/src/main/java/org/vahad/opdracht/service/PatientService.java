package org.vahad.opdracht.service;

import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.vahad.opdracht.domein.PatientFilter;
import org.vahad.opdracht.dto.PatientDto;

/**
 * Service for crud operations on {@link org.vahad.opdracht.domein.Patient} object
 *
 * @author Daniël Bosmans
 */
@ParametersAreNonnullByDefault
public interface PatientService {
    
    /**
     * Searches for patients in the database matching the given filter criteria {@link PatientFilter}
     *
     * @param filter {@link PatientFilter} containing zero or more filter criteria
     * @return list of {@link PatientDto} objects
     */
    List<PatientDto> searchPatients(final PatientFilter filter);
    
    /**
     * Registers a new patient
     *
     * @param patientDto {@link PatientDto} that contains information about the new patient
     * @return true registration has succeed, false when error has occurred
     */
    boolean registerPatient(final PatientDto patientDto);

}
