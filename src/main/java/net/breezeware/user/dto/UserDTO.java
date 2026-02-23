package net.breezeware.user.dto;

import net.breezeware.user.entity.Role;

public class UserDTO {

    private int id;
    private String name;
    private String email;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(int id, String name, String email, Role role) {
        this.id    = id;
        this.name  = name;
        this.email = email;
        this.role  = role;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }



    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}