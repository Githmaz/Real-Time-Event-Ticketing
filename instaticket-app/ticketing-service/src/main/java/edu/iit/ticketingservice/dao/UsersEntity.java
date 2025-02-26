package edu.iit.ticketingservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.iit.ticketingservice.dto.UserRole;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;  // Database-generated ID

    @Column(unique = true, nullable = false)
    private String userId;  // Custom user identifier (e.g., C-, V-, A-)

    @Column(unique = true, nullable = false)
    private String username;  // Unique username

    @Column(unique = true, nullable = false)
    private String email;  // Unique email

    @Column(nullable = false)
    @JsonIgnore
    private String password;  // User password

    @Column(nullable = false)
    private String name;  // User's full name or company name

    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    @Column(nullable = false)
    private UserRole userRole;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

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

    public UsersEntity(Long id, String userId, String username, String password, String email, String name) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public UsersEntity() {
    }

    public void generateUserId() {
        this.setUserId("N/A-" +this.id);
    }
}
