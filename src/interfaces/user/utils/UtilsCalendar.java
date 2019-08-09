package interfaces.user.utils;

import model.Calendar;

import javax.naming.directory.InvalidAttributesException;

import static interfaces.user.utils.UtilsSystem.readEntries;

public class UtilsCalendar {
    private static int getWeek() {
        System.out.print("\t\tDay of Week (De 1 a 7): ");
        return readEntries(1, 7);
    }

    private static int getDayMonth() {
        System.out.print("\t\tDay of Month: ");
        return readEntries(1, 31);
    }

    private static int getMonth() {
        System.out.print("\t\tMonth: ");
        return readEntries(1, 11);
    }

    private static int getYear() {
        System.out.print("\t\tYear: ");
        return readEntries(0, Integer.MAX_VALUE);
    }

    private static int getHourDay() {
        System.out.print("\t\tHour of day: ");
        return readEntries(0, 24);
    }

    private static int getMinutes() {
        System.out.print("\t\tMinute: ");
        return readEntries(0, 60);
    }

    public static Calendar getDate() {
        System.out.print("\nEnter with data(Only in numbers):");

        int day_of_week = getWeek();
        int day_of_month = getDayMonth();
        int month = getMonth();
        int year = getYear();
        int hour = getHourDay();
        int minute = getMinutes();

        try {
            return new Calendar(year, month, day_of_month, day_of_week, hour, minute);
        } catch (InvalidAttributesException e) {
            System.out.println("Error in your date, please enter that again!\n");
            return getDate();
        }
    }
}
