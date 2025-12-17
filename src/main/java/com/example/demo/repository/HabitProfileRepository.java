package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.HabitProfile;
import java.util.Optional;

public interface HabitProfileRepository extends JpaRepository<HabitProfile, Long> {

    Optional<HabitProfile> findByStudentId(Long studentId);
}
