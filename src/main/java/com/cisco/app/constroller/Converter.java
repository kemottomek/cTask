package com.cisco.app.constroller;
/*
 * @project cisco-task
 * @author nbtwszol
 */

import com.cisco.app.entity.CaseEntity;
import com.cisco.app.entity.NoteEntity;
import com.cisco.app.entity.UserEntity;
import com.cisco.app.generated.model.ModelCase;
import com.cisco.app.generated.model.Note;
import com.cisco.app.generated.model.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Converter {

    public User convert(UserEntity userEntity) {
        if (userEntity == null) return null;
        var result = new User();
        result.setEmail(userEntity.getEmail());
        result.setFirstname(userEntity.getFirstName());
        result.setLastname(userEntity.getLastname());
        result.setId(userEntity.getId());
        return result;
    }

    public Note convert(NoteEntity noteEntity) {
        if (noteEntity == null) return null;
        var result = new Note();
        result.setDetails(noteEntity.getDetails());
        result.setId(noteEntity.getId());
        return result;
    }

    public ModelCase convert(CaseEntity caseEntity) {
        if (caseEntity == null) return null;
        var result = new ModelCase();
        result.setDescription(caseEntity.getDescription());
        result.setId(caseEntity.getId());
        result.setSeverity(caseEntity.getSeverity());
        result.setTitle(caseEntity.getTitle());
        result.setStatus(caseEntity.getStatus());
        result.setUser(convert(caseEntity.getUser()));
        if (caseEntity.getNotes() != null && caseEntity.getNotes().size() > 0) {
            var notes = caseEntity.getNotes().stream().map(this::convert).collect(Collectors.toSet());
            result.setNotes(notes);
        }
        return result;
    }

    public CaseEntity convert(ModelCase aCase) {
        var caseEntity = new CaseEntity();
        caseEntity.setDescription(aCase.getDescription());
        caseEntity.setStatus(aCase.getStatus());
        caseEntity.setTitle(aCase.getTitle());
        caseEntity.setSeverity(aCase.getSeverity());
        caseEntity.setUser(convert(aCase.getUser()));
        if (aCase.getNotes() != null) {
            var notes = aCase.getNotes().stream().map(this::convert).collect(Collectors.toList());
            caseEntity.setNotes(notes);
        }
        return caseEntity;
    }

    public NoteEntity convert(Note note) {
        if (note == null) return null;
        var result = new NoteEntity();
        result.setDetails(note.getDetails());
        result.setId(note.getId());
        return result;

    }

    public UserEntity convert(User user) {
        if (user == null) return null;
        var result = new UserEntity();
        result.setFirstName(user.getFirstname());
        result.setId(user.getId());
        result.setEmail(user.getEmail());
        result.setLastname(user.getLastname());
        return result;

    }
}
