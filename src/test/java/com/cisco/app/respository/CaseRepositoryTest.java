package com.cisco.app.respository;

import com.cisco.app.entity.CaseEntity;
import com.cisco.app.entity.NoteEntity;
import com.cisco.app.entity.UserEntity;
import com.cisco.app.generated.model.ModelCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.liquibase.enabled=false"
})
@DataJpaTest
class CaseRepositoryTest {
    @Autowired
    private CaseEntityRepository caseRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void saveCaseOpen() {
        //given
        var userEntity = new UserEntity();
        userEntity.setEmail("test@test.pl");
        userEntity.setFirstName("firstName");
        userEntity.setLastname("lastName");

        var noteEntity = new NoteEntity();
        noteEntity.setDetails("details");

        var newCase = new CaseEntity();
        newCase.setStatus(ModelCase.StatusEnum.OPEN);
        newCase.setDescription("description");
        newCase.setSeverity(0);
        newCase.setUser(userEntity);
        newCase.setNotes(List.of(noteEntity));
        newCase.getUser().setCases(List.of(newCase));
        newCase.getNotes().forEach(entity -> entity.setCaseEntity(newCase));

        //when
        entityManager.persist(newCase);
        entityManager.flush();
        List<CaseEntity> resultAll = caseRepository.findAll();
        List<CaseEntity> resultByStatus = caseRepository.findByStatus(ModelCase.StatusEnum.OPEN);

        List<CaseEntity> resultByUser = caseRepository.findByUserId(resultAll.get(0).getUser().getId());
        List<CaseEntity> resultByUserAndStatus = caseRepository.findByStatusAndUser(ModelCase.StatusEnum.OPEN, resultAll.get(0).getUser().getId());

        //then
        assertThat(resultAll.size()).isEqualTo(1);
        assertThat(resultAll.get(0).getUser().getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(resultAll.get(0).getNotes().size()).isEqualTo(1);
        assertThat(resultAll.get(0).getNotes().get(0).getDetails()).isEqualTo(noteEntity.getDetails());

        assertThat(resultByStatus.size()).isEqualTo(1);
        assertThat(resultByStatus.get(0).getUser().getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(resultByStatus.get(0).getNotes().size()).isEqualTo(1);
        assertThat(resultByStatus.get(0).getNotes().get(0).getDetails()).isEqualTo(noteEntity.getDetails());

        assertThat(resultByUser.size()).isEqualTo(1);
        assertThat(resultByUser.get(0).getUser().getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(resultByUser.get(0).getNotes().size()).isEqualTo(1);
        assertThat(resultByUser.get(0).getNotes().get(0).getDetails()).isEqualTo(noteEntity.getDetails());

        assertThat(resultByUserAndStatus.size()).isEqualTo(1);
        assertThat(resultByUserAndStatus.get(0).getUser().getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(resultByUserAndStatus.get(0).getNotes().size()).isEqualTo(1);
        assertThat(resultByUserAndStatus.get(0).getNotes().get(0).getDetails()).isEqualTo(noteEntity.getDetails());

    }


}