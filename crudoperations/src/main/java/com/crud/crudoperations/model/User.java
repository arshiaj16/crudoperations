package com.crud.crudoperations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    @Column(nullable = false, length = 10)
    private String mobile;

    @Min(value = 0, message = "Age must be non-negative")
    @Column(nullable = false)
    private int age;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @Past(message = "Date of birth must be in the past")
    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String address;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public int getAge() { return age; }
    public String getPassword() { return password; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getAddress() { return address; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public void setAge(int age) { this.age = age; }
    public void setPassword(String password) { this.password = password; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setAddress(String address) { this.address = address; }
}
