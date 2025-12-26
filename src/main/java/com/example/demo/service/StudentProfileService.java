// StudentProfileService.java
package com.example.demo.service;

import com.example.demo.model.StudentProfile;

import java.util.List;
import java.util.Optional;

public interface StudentProfileService {

    StudentProfile createStudent(StudentProfile student);

    StudentProfile getStudentById(Long id);

    StudentProfile updateStudentStatus(Long id, boolean active);

    List<StudentProfile> getAllStudents();

    Optional<StudentProfile> findByStudentId(String studentId);
}
