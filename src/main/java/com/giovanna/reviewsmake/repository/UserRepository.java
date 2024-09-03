package com.giovanna.reviewsmake.repository;

import com.giovanna.reviewsmake.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    /*case-sensitive query*/
    @Query(value = "SELECT * FROM tb_user WHERE BINARY user_name = :username", nativeQuery = true)
    Optional<UserModel> findByUsername(@Param("username") String username);
    Optional<UserModel> findByEmail(String email);
}

