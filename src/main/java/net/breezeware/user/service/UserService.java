package net.breezeware.user.service;

import net.breezeware.user.dao.UserDao;
import net.breezeware.user.dto.UserDTO;
import net.breezeware.user.entity.Role;

import java.util.Scanner;

public class UserService {

    private final UserDao userDao;
    private final Scanner scanner;

    public UserService() {
        this.userDao = new UserDao();
        this.scanner = new Scanner(System.in);
    }

    // ─── Register ────────────────────────────────────────────────
    public void register(Role role) {
        System.out.println("\n=== REGISTER ===");

        System.out.print("Name    : ");
        String name = scanner.nextLine().trim();

        System.out.print("Email   : ");
        String email = scanner.nextLine().trim();

        // Check if email already exists
        if (userDao.emailExists(email)) {
            System.out.println("\n✘ Email already registered. Please use a different email.");
            return;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        int userId = userDao.registerUser(name, email, password, role);

        if (userId > 0) {
            System.out.println("\n✔ Registered successfully! You can now login.");
        } else {
            System.out.println("\n✘ Registration failed. Please try again.");
        }
    }

    // ─── Login ───────────────────────────────────────────────────
    public UserDTO login(Role expectedRole) {
        System.out.print("\nEmail: ");
        String email = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        UserDTO user = userDao.login(email, password, expectedRole);

        if (user != null) {
            System.out.println("\n✔ Login successful! Welcome, " + user.getName() + " (" + user.getRole() + ")");
            return user;
        } else {
            System.out.println("\n✘ Invalid email or password, or you don't have " + expectedRole + " privileges.");
            return null;
        }
    }

    // ─── Login or Register Menu ──────────────────────────────────
    public UserDTO loginOrRegister(Role role) {
        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1 -> {
                    UserDTO user = login(role);
                    if (user != null) return user;
                }
                case 2 -> register(role);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
