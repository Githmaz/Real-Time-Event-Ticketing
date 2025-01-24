package edu.iit.ticketingservice.dto.users;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class UserReq {

    @Valid
    @NotNull(message = "User object cannot be null")
    private Users user;

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

}
