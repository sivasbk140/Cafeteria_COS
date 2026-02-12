package net.breezeware.user.service;

import net.breezeware.user.dao.UserDao;
import net.breezeware.user.entity.Role;
import net.breezeware.user.entity.User;

public class UserService {

    private final UserDao userDao = new UserDao();

    public void register(String name, String email, String password, Role role)
            throws Exception {

        if (userDao.existsByEmail(email)) {
            System.out.println("User already exists");
            return;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        userDao.create(user);
        System.out.println("Registration successful. Please login.");
    }

    public User login(String email, String password) throws Exception {
        return userDao.login(email, password);
    }
}
