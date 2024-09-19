package com.learntoearn.learntoearn.DTO;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private String examPreparingFor;
    private String role;

    // Constructors, Getters, and Setters

    public UserResponseDTO(Long id, String name, String email, String phoneNumber, String gender, String examPreparingFor, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.examPreparingFor = examPreparingFor;
        this.role = role;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExamPreparingFor() {
        return examPreparingFor;
    }

    public void setExamPreparingFor(String examPreparingFor) {
        this.examPreparingFor = examPreparingFor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

    
    
}
