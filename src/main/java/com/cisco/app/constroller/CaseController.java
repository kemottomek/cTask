package com.cisco.app.constroller;
/*
 * @author nbtwszol
 */

import com.cisco.app.exeption.ControllerException;
import com.cisco.app.generated.api.CaseControllerApi;
import com.cisco.app.generated.model.ModelCase;
import com.cisco.app.generated.model.Note;
import com.cisco.app.service.CaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Slf4j
public class CaseController implements CaseControllerApi {
    private final CaseService caseService;


    @Override
    public ResponseEntity<Note> addNote(Long caseId, Note note) {
        var result = caseService.addNote(caseId, note.getDetails());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ModelCase> createCase(ModelCase modelCase) {
        var result = caseService.createCase(modelCase).orElseThrow(() -> new ControllerException("creation error", HttpStatus.INTERNAL_SERVER_ERROR));
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ModelCase> getCase(Long caseId) {
        var result = caseService.getCaseByCaseId(caseId).orElseThrow(() -> new ControllerException(caseId, HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<ModelCase>> getCasesWithStatus(String status) {
        var result = caseService.getCaseByStatus(ModelCase.StatusEnum.fromValue(status));
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<ModelCase>> getOpenCases(Long userId) {
        var result = caseService.getCaseByUserIdAndStatus(ModelCase.StatusEnum.OPEN, userId);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<ModelCase>> getOpenCases2(Long userId, String status) {
        var result = caseService.getCaseByUserIdAndStatus(ModelCase.StatusEnum.fromValue(status), userId);
        return ResponseEntity.ok(result);
    }
}
