package net.breezeware.food.enumeration;

public enum MenuDay {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY,
    ALLDAY;


    public static MenuDay fromString(String day) {
        try {
            return MenuDay.valueOf(day.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Invalid day value in DB → '" + day + "'");
            return null;
        }
    }
}