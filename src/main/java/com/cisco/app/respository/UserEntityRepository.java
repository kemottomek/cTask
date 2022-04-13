package com.cisco.app.respository;
/*
 * @author nbtwszol
 */

import com.cisco.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

}
