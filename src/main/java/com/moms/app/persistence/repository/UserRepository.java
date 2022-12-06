package com.moms.app.persistence.repository;

import com.moms.app.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String userName);
    Optional<UserEntity> findByLocation(String location);


    @Query
    (value = "SELECT * FROM users u WHERE u.user_name LIKE '%keyword%' or u.location like '%keyword'", nativeQuery = true)
    List<UserEntity> findByKeyword(@Param("keyword") String keyword);
}

