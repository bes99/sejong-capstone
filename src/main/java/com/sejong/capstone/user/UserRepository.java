package com.sejong.capstone.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    @Modifying
    @Query("UPDATE User u SET u.sex = :sex, u.birth = :birth WHERE u.id = :id")
    void setUserSexAndBirth(Long id, String sex, String birth);
}
