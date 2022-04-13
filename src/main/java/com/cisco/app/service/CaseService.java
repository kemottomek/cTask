package com.cisco.app.service;
/*
 * @author nbtwszol
 */

import com.cisco.app.constroller.Converter;
import com.cisco.app.entity.NoteEntity;
import com.cisco.app.generated.model.ModelCase;
import com.cisco.app.generated.model.Note;
import com.cisco.app.respository.CaseEntityRepository;
import com.cisco.app.respository.NoteEntityRepository;
import com.cisco.app.util.TransactionHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CaseService {
    private final NoteEntityRepository noteEntityRepository;
    private final CaseEntityRepository caseEntityRepository;
    private final TransactionHandler transactionHandler;
    private final Converter converter;


    public Optional<ModelCase> getCaseByCaseId(Long caseId) {
        var result = caseEntityRepository.findByCaseId(caseId);
        return result.map(converter::convert);
    }

    public List<ModelCase> getCaseByUserId(Long userId) {
        var result = caseEntityRepository.findByUserId(userId);
        return result.stream().map(converter::convert).collect(Collectors.toList());
    }

    public List<ModelCase> getCaseByUserIdAndStatus(ModelCase.StatusEnum status, Long userId) {
        var result = caseEntityRepository.findByStatusAndUser(status, userId);
        return result.stream().map(converter::convert).collect(Collectors.toList());
    }

    public List<ModelCase> getCaseByStatus(ModelCase.StatusEnum status) {
        var result = caseEntityRepository.findByStatus(status);
        return result.stream().map(converter::convert).collect(Collectors.toList());
    }

    public Optional<ModelCase> createCase(ModelCase aCase) {
        return transactionHandler.runInTransaction(() -> {
                    var caseEntity = converter.convert(aCase);
                    caseEntity.getUser().setCases(List.of(caseEntity));
                    caseEntity.getNotes().forEach(noteEntity -> noteEntity.setCaseEntity(caseEntity));
                    return Optional.of(converter.convert(caseEntityRepository.save(caseEntity)));
                }
        );
    }

    public Note addNote(Long caseId, String details) {
        return transactionHandler.runInTransaction(() -> {
                    var caseEntity = caseEntityRepository.getById(caseId);
                    var note = new NoteEntity();
                    note.setDetails(details);
                    note.setCaseEntity(caseEntity);
                    return converter.convert(noteEntityRepository.save(note));
                }
        );
    }


}
