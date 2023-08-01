package com.example.Education.repository;

import com.example.Education.model.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findUserRoleByTitle(String title);

    Optional<UserRoleEntity> findUserRoleById(Long id);
}
