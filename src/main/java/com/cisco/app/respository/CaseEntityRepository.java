package com.cisco.app.respository;
/*
 * @author nbtwszol
 */

import com.cisco.app.entity.CaseEntity;
import com.cisco.app.generated.model.ModelCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaseEntityRepository extends JpaRepository<CaseEntity, Long> {

    @Query(value = "SELECT c FROM CaseEntity c JOIN FETCH c.user a WHERE c.id = :caseId ")
    Optional<CaseEntity> findByCaseId(Long caseId);

    @Query(value = "SELECT c FROM CaseEntity c JOIN FETCH c.user a WHERE a.id = :userId ")
    List<CaseEntity> findByUserId(Long userId);

    @Query(value = "SELECT c FROM CaseEntity c JOIN FETCH c.user a WHERE c.status = :status ")
    List<CaseEntity> findByStatus(ModelCase.StatusEnum status);

    @Query(value = "SELECT c FROM CaseEntity c JOIN FETCH c.user a WHERE c.status = :status AND a.id = :userId ")
    List<CaseEntity> findByStatusAndUser(ModelCase.StatusEnum status, Long userId);
}
