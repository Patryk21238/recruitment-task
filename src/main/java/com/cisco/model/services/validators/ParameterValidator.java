package com.cisco.model.services.validators;

import com.cisco.model.models.Case;
import com.cisco.model.models.enums.Severity;
import com.cisco.model.models.enums.Status;
import com.cisco.model.services.exceptions.InvalidParameterProvidedException;

import java.util.Date;

public class ParameterValidator {
    private static ParameterValidator validator;

    private ParameterValidator() {
    }

    public static ParameterValidator getInstance() {
        if (validator == null) {
            validator = new ParameterValidator();
        }
        return validator;
    }

    public void caseValidation(Case caseForValidation) {
        caseValidationForNullValues(caseForValidation);
        tittleValidation(caseForValidation.getTitle());
        descriptionValidation(caseForValidation.getDescription());
    }

    public void caseValidationForNullValues(Case caseForValidation){
        if(caseForValidation.getTitle() == null){
            throw new NullPointerException("The given tittle must not be null.");
        }
        if(caseForValidation.getDescription() == null){
            throw new NullPointerException("The given description must not be null.");
        }
        if(caseForValidation.getCreationDate() == null){
            throw new NullPointerException("The given date must not be null.");
        }
        if(caseForValidation.getSeverity() == null){
            throw new NullPointerException("The given severity must not be null.");
        }
        if(caseForValidation.getStatus() == null){
            throw new NullPointerException("The given status must not be null.");
        }
    }

    public void idValidation(Long id) {
        if (id <= 0) {
            throw new InvalidParameterProvidedException("Id should be greater than 0.");
        }
    }

    private void tittleValidation(String tittle) {
        if (!(tittle.length() > 0) || !(tittle.length() < 80)) {
            throw new InvalidParameterProvidedException("Tittle should contain less than 80 characters.");
        }
    }

    private void descriptionValidation(String description) {
        if (!(description.length() > 10) || !(description.length() < 2000)) {
            throw new InvalidParameterProvidedException("Description should contain between 10 and 2000 characters.");
        }
    }
}