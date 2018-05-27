package org.vahad.opdracht.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.ParametersAreNonnullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vahad.opdracht.Mappers.ServiceMapper;
import org.vahad.opdracht.dao.PatientRepository;
import org.vahad.opdracht.dao.PatientSpecification;
import org.vahad.opdracht.domein.PatientFilter;
import org.vahad.opdracht.dto.PatientDto;

/**
 * implements {@link PatientService}
 *
 * @author Daniël Bosmans
 */
@Service
@Transactional
@ParametersAreNonnullByDefault
public class PatientServiceImpl implements PatientService {
    
    @Autowired
    private PatientRepository patientRepository;
    
    public List<PatientDto> searchPatients(final PatientFilter filter){
        return patientRepository.findAll(new PatientSpecification(filter)).stream()
                .map(ServiceMapper::mapPatientDomeinToDto)
                .sorted(Comparator.comparing(PatientDto::getFirstName))
                .collect(Collectors.toList());
    }
    
    public boolean registerPatient(final PatientDto patientDto){
        if(patientRepository.findPatientByPatientId(patientDto.getPatientId()) != null){
            return false;
        }
        patientRepository.save(ServiceMapper.mapPatientDtoToDomein(patientDto));
        return true;
    }
}
