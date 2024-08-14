package com.giovanna.projectsti.repository;

import com.giovanna.projectsti.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
}
