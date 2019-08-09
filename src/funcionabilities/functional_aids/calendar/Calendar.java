package funcionabilities.functional_aids.calendar;

import javax.naming.directory.InvalidAttributesException;

@SuppressWarnings("CanBeFinal")
public class Calendar implements Cloneable {
    public static final int DAY_OF_MONTH = 2;
    public static final int DAY_OF_WEEK = 3;
    public static final int MINUTE = 5;
    private static final int YEAR = 0;
    private static final int MONTH = 1;
    private static final int HOUR = 4;
    private static final String[] nameOfDays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static final String[] nameOfMonth = new String[]{"January", "February", "March", "April", "June", "July", "August", "September", "October1",
            "November", "December"};
    private static int[] limitDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int dayOfWeek;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;


    public Calendar(int year, int month, int day, int dayOfWeek, int hour, int minute) throws InvalidAttributesException {
        if (year < 0 || month < 0 || month > 11 || hour < 0 || hour > 24 || minute < 0 || minute > 60) {
            throw new InvalidAttributesException("Error in date values\n");
        }

        if (itsLeap(year)) {
            limitDays[1] = 29;
        } else {
            limitDays[1] = 28;
        }

        if (day > limitDays[month]) {
            throw new InvalidAttributesException("Error in date values\n");
        }

        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.minute = minute;
    }

    private static boolean itsLeap(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    static double getDeltaMinutes(Calendar start, Calendar end) {
        return (end.getAllMinutes() - start.getAllMinutes());
    }

    public int get(int type) {
        switch (type) {
            case YEAR:
                return year;

            case MONTH:
                return month;

            case DAY_OF_MONTH:
                return day;

            case DAY_OF_WEEK:
                return dayOfWeek;

            case HOUR:
                return hour;

            case MINUTE:
                return minute;
        }

        return -1;
    }


    private double getAllMinutes() {
        double time = year * 12;
        time += month;
        time *= 30;
        time += day;
        time *= 24;
        time += hour;
        time *= 60;
        time += minute;

        return time;
    }

    private void addMinute(int amount) {
        while (amount > 0) {
            minute++;
            if (minute >= 60) {
                addHour(1);
                minute = 0;
            }

            amount--;
        }
    }

    private void addHour(int amount) {
        while (amount > 0) {
            hour++;
            if (hour >= 24) {
                addDay(1);
                hour = 0;
            }

            amount--;
        }
    }

    private void addDay(int amount) {
        while (amount > 0) {
            day++;
            if (day > limitDays[month]) {
                addMonth(1);
                day = 1;
            }

            dayOfWeek++;
            if (dayOfWeek > 7) {
                dayOfWeek = 1;
            }
            month = 0;

            amount--;
        }
    }

    private void addMonth(int amount) {
        while (amount > 0) {
            month += 1;

            if (month > 11) {
                addYear(1);
                month = 1;
            }

            amount--;
        }
    }

    private void addYear(int amount) {
        year += amount;
    }

    public void add(int type, int amount) throws InvalidAttributesException {
        if (type < 0 || type > 5 || type == 3) {
            throw new InvalidAttributesException("Type most be ");
        }

        switch (type) {
            case YEAR:
                addYear(amount);

            case MONTH:
                addMonth(amount);

            case DAY_OF_MONTH:
                addDay(amount);

            case HOUR:
                addHour(amount);

            case MINUTE:
                addMinute(amount);
        }
    }

    public String toString() {
        return "Date: " + nameOfDays[dayOfWeek - 1] + " " + day + " / " + nameOfMonth[month] + " / " + year + "\n\t" + hour + ":" + minute + "\n";
    }

    public int getMaximum(int type) {
        if (type == DAY_OF_MONTH) {
            return limitDays[month];
        }

        return -1;
    }

    public boolean equals(Calendar other) {
        return year == other.year && month == other.month && day == other.day && dayOfWeek == other.dayOfWeek &&
                hour == other.hour && minute == other.minute;
    }

    public Calendar clone() throws CloneNotSupportedException {
        return (Calendar) super.clone();
    }
}
