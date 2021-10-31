package com.cisco.controller;

import com.cisco.model.models.Case;
import com.cisco.model.services.CaseService;
import com.cisco.model.services.exceptions.InvalidParameterProvidedException;
import com.cisco.model.services.exceptions.NoEntityForThisIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cases")
public class CaseController {
    @Autowired
    private CaseService caseService;

    @PostMapping
    public ResponseEntity<?> addNewCaseToDB(@RequestBody Case newCase) {
        try {
            caseService.saveCase(newCase);
        } catch (InvalidParameterProvidedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{caseId}")
    public ResponseEntity<?> getCaseById(@PathVariable Long caseId) {
        Case caseById = caseService.getCaseById(caseId);

        return new ResponseEntity<>(caseById, HttpStatus.OK);
    }

    @DeleteMapping("/{caseId}")
    public ResponseEntity<?> removeCaseById(@PathVariable Long caseId) {
        try {
            caseService.deleteCaseById(caseId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateCase(@RequestBody Case newCase) {
        Case updatedCase;
        try {
            updatedCase = caseService.updateCase(newCase);
        } catch (NoEntityForThisIdException | NullPointerException | InvalidParameterProvidedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedCase, HttpStatus.OK);
    }
}
