package com.example.Education.repository;



import com.example.Education.model.UserEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM #{#entityName} u WHERE user_name =  ?1 ")
    Optional<UserEntity> findUserByUsername(String username);

    @Query("SELECT u FROM #{#entityName} u WHERE email =  ?1 ")
    Optional<UserEntity> findUserByEmail(String email);

    @Query("SELECT u FROM #{#entityName} u WHERE phone_number =  ?1 ")
    Optional<UserEntity> findUserByPhoneNumber(String phone);
}
