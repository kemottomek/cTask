package com.cisco.app.conf;
/*
 * @author nbtwszol
 */

import com.cisco.app.entity.CaseEntity;
import com.cisco.app.entity.NoteEntity;
import com.cisco.app.entity.UserEntity;
import com.cisco.app.generated.model.ModelCase;
import com.cisco.app.respository.CaseEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LoadData implements ApplicationRunner {

    @Autowired
    private final CaseEntityRepository caseRepository;

    @Override
    public void run(ApplicationArguments args) {
        var caseEntity = new CaseEntity();
        caseEntity.setStatus(ModelCase.StatusEnum.OPEN);
        caseEntity.setDescription("desc");
        caseEntity.setSeverity(1);
        caseEntity.setTitle("tit");

        var userEntity = new UserEntity();
        userEntity.setEmail("tomasz.wszol@gmail.com");
        userEntity.setLastname("w");
        userEntity.setFirstName("t");
        userEntity.setCases(List.of(caseEntity));
        caseEntity.setUser(userEntity);

        var noteEntity = new NoteEntity();
        noteEntity.setDetails("s");
        noteEntity.setCaseEntity(caseEntity);
        caseEntity.setNotes(List.of(noteEntity));

        //TODO clean test db
       // caseRepository.save(caseEntity);

    }
}
