package edu.iit.ticketingservice.dto;

import jakarta.validation.constraints.*;
import org.hibernate.usertype.UserType;

public class Users {

    private String userId;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "User type cannot be blank")
    private UserRole userRole;


    public @NotNull(message = "User type cannot be blank") UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(@NotNull(message = "User type cannot be blank") UserRole userRole) {
        this.userRole = userRole;
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

    public Users(Long id, String userId, String username, String password, String email, String name) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public Users() {
     }
}
