package net.breezeware.user.entity;

import java.time.Instant;

public class User {

    private int id;
    private String name;
    private String password;
    private String email;
    private Role role;

    private Instant createdOn;
    private Instant updatedOn;

}

