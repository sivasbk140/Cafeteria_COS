package net.breezeware.food.entity;

public enum MenuDay {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    // safely convert String from DB to enum
    public static MenuDay fromString(String day) {
        try {
            return MenuDay.valueOf(day.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Invalid day value in DB → '" + day + "'");
            return null;
        }
    }
}