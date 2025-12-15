package com.example.demo.model;

import java.time.LocalDateTime;

public class StudentProfile {
    private long id;
    private String studentId;
    private String fullName;
    private String email;
    private String department;
    private int yearLevel;
    private boolean active;
    private LocalDateTime createdAt;
}
public StudentProfile(){}
public StudentProfile(String studentId, String fullName, String email, String department, int yearLevel, boolean active,
        LocalDateTime createdAt) {
    this.studentId = studentId;
    this.fullName = fullName;
    this.email = email;
    this.department = department;
    this.yearLevel = yearLevel;
    this.active = active;
    this.createdAt = createdAt;
}
public void setId(long id) {
    this.id = id;
}
public void setStudentId(String studentId) {
    this.studentId = studentId;
}
public void setFullName(String fullName) {
    this.fullName = fullName;
}
public void setEmail(String email) {
    this.email = email;
}
public void setDepartment(String department) {
    this.department = department;
}
public void setYearLevel(int yearLevel) {
    this.yearLevel = yearLevel;
}
public void setActive(boolean active) {
    this.active = active;
}
public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
}
public long getId() {
    return id;
}
public String getStudentId() {
    return studentId;
}
public String getFullName() {
    return fullName;
}
public String getEmail() {
    return email;
}
public String getDepartment() {
    return department;
}
public int getYearLevel() {
    return yearLevel;
}
public boolean isActive() {
    return active;
}
public LocalDateTime getCreatedAt() {
    return createdAt;
}
