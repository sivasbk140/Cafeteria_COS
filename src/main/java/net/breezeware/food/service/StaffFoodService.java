package net.breezeware.food.service;

import net.breezeware.food.dao.CustomerMenuDao;
import net.breezeware.food.dto.MenuViewDTO;
import net.breezeware.order.service.StaffOrderService;

import java.util.List;
import java.util.Scanner;

public class StaffFoodService extends FoodService {

    private final CustomerMenuDao customerMenuDao;
    private final StaffOrderService orderService;
    private final Scanner scanner;

    public StaffFoodService() {
        this.customerMenuDao = new CustomerMenuDao();
        this.orderService = new StaffOrderService();
        this.scanner = new Scanner(System.in);
    }

    //  Main Staff Menu
    public void showStaffMenu() {
        while (true) {
            System.out.println("\n=== STAFF MENU ===");
            System.out.println("1. View Full Weekly Menu");
            System.out.println("2. View Active Orders");
            System.out.println("3. View Order Details");
            System.out.println("4. Update Order Status");
            System.out.println("5. View Cancelled Orders");
            System.out.println("6. View Completed Orders");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {
                case 1 -> viewFullWeekMenu();
                case 2 -> orderService.viewActiveOrders();
                case 3 -> orderService.viewOrderDetails();
                case 4 -> orderService.updateOrderStatus();
                case 5 -> orderService.viewCancelledOrders();
                case 6 -> orderService.viewCompletedOrders();
                case 7 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    //  View Full Weekly Menu
    public void viewFullWeekMenu() {
        System.out.println("\n=== FULL WEEKLY MENU ===");
        List<MenuViewDTO> menuItems = customerMenuDao.getFullWeekMenu();
        displayMenu(menuItems);
    }
}