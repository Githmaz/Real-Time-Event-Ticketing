package edu.iit.ticketingservice.dao;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@Data
@SuperBuilder
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generated primary key
    private Long id;

    @Column(unique = true)  // Ensure that the customUserId is unique
    private String userId;

    @Column(unique = true, nullable = false)  // Username must be unique and not null
    private String username;

    @Column(nullable = false)  // Password is mandatory
    private String password;

    @Column(unique = true, nullable = false)  // Email must be unique and not null
    private String email;

    @Column(nullable = false)  // Name is mandatory, can be personal or company name
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserEntity(Long id, String userId, String username, String password, String email, String name) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public UserEntity() {
    }

    public void generateUserId() {
        this.setUserId("N/A-" +this.id);
    }
}
