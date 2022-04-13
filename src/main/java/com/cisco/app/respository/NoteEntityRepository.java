package com.cisco.app.respository;

/*
 * @project cTask
 * @author nbtwszol
 */

import com.cisco.app.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteEntityRepository extends JpaRepository<NoteEntity, Long> {
}
