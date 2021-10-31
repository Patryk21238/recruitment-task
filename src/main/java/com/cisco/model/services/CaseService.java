package com.cisco.model.services;

import com.cisco.model.daos.CaseDao;
import com.cisco.model.models.Case;
import com.cisco.model.services.exceptions.NoEntityForThisIdException;
import com.cisco.model.services.validators.ParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaseService {
    private final ParameterValidator validator = ParameterValidator.getInstance();
    @Autowired
    private CaseDao caseDao;

    public void saveCase(Case newCase) {
        validator.caseValidation(newCase);
        caseDao.save(newCase);
    }

    public Case getCaseById(Long id) {
        validator.idValidation(id);
        Optional<Case> optionalCase = caseDao.findById(id);
        if (optionalCase.isEmpty()) {
            throw new IllegalArgumentException("There is no case for provided id.");
        }
        return optionalCase.get();
    }

    public void deleteCaseById(Long id) {
        validator.idValidation(id);
        try {
            caseDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No entity with provided id.");
        }
    }

    public Case updateCase(Case newCase) {
        validator.caseValidation(newCase);
        if (newCase.getId() == null) {
            throw new NullPointerException("The given id must not be null.");
        }
        Optional<Case> optionalCaseToUpdate = caseDao.findById(newCase.getId());
        if (optionalCaseToUpdate.isEmpty()) {
            throw new NoEntityForThisIdException("There is no case for provided id.");
        }

        Case caseToUpdate = optionalCaseToUpdate.get();

        caseToUpdate.setStatus(newCase.getStatus());
        caseToUpdate.setTitle(newCase.getTitle());
        caseToUpdate.setDescription(newCase.getDescription());
        caseToUpdate.setSeverity(newCase.getSeverity());
        caseToUpdate.setCreationDate(newCase.getCreationDate());


        caseDao.save(caseToUpdate);

        return caseToUpdate;
    }
}