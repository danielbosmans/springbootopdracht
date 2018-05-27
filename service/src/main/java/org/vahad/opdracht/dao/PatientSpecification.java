package org.vahad.opdracht.dao;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.vahad.opdracht.domein.Patient;
import org.vahad.opdracht.domein.PatientFilter;

/**
 * Implementation of {@link Specification}
 * Class search predicates {@link PatientFilter} used for searching {@link Patient} objects.
 *
 * @author Daniël Bosmans
 */
@NoRepositoryBean
@ParametersAreNonnullByDefault
public final class PatientSpecification implements Specification<Patient> {
    private final PatientFilter filter;
    
    public PatientSpecification(final PatientFilter filter) {
        super();
        this.filter = filter;
    }
    
    @Override
    public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction(); //and function
        if(Strings.isNotEmpty(filter.getFirstName())){
            predicate.getExpressions()
                    .add(criteriaBuilder.equal(root.get(Patient.FIRSTNAME_COLUMN), filter.getFirstName()));
        }
        if(Strings.isNotEmpty(filter.getLastName())){
            predicate.getExpressions()
                    .add(criteriaBuilder.equal(root.get(Patient.LASTNAME_COLUMN), filter.getLastName()));
        }
        if(Strings.isNotEmpty(filter.getPatientId())){
            predicate.getExpressions()
                    .add(criteriaBuilder.equal(root.get(Patient.PATIENT_ID_COLUMN), filter.getPatientId()));
        }
        if(filter.getDateOfBirth() != null){
            predicate.getExpressions()
                    .add(criteriaBuilder.greaterThan(root.get(Patient.DATE_OF_BIRTH_COLUMN), filter.getDateOfBirth()));
        }
        return predicate;
    }
}
