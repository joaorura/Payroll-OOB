package funcionabilities.functional_aids.calendar;

import javax.naming.directory.InvalidAttributesException;

public class Calendar implements Cloneable{
    public static final int YEAR = 0;
    public static final int MONTH = 1;
    public static final int DAY_OF_MONTH = 2;
    public static final int DAY_OF_WEEK = 3;
    public static final int HOUR = 4;
    public static final int MINUTE = 5;

    private static int[] limitDays = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static String[] nameOfDays = new String[] {"Monday", "Tuesday" , "Wednesday" , "Thursday" , "Friday" , "Saturday" , "Sunday"};
    private static String[] nameOfMonth = new String[] {"January", "February", "March", "April", "June", "July", "August", "September", "October1",
            "November", "December"};

    private int dayOfWeek;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;


    public Calendar(int year, int month, int day, int dayOfWeek, int hour, int minute) throws InvalidAttributesException {
        if(year < 0 || month < 0 || month > 11 || hour < 0 || hour > 24 || minute < 0 || minute > 60) {
            throw new InvalidAttributesException("Error in date values\n");
        }

        if(itsBissext(year)) {
            limitDays[1] = 29;
        }
        else {
            limitDays[1] = 28;
        }

        if(day > limitDays[month]) {
            throw new InvalidAttributesException("Error in date values\n");
        }

        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.minute = minute;
    }

    private static boolean itsBissext(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    public static double getDeltaMinutes (Calendar start, Calendar end) {
        return (end.getAllMinutes() - start.getAllMinutes());
    }

    public int get(int type) {
        switch(type) {
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
    

    public double getAllMinutes() {
        double time = year * 12;
        time += month;
        time *= 30;
        time += day;
        time *= 24;
        time += minute;
        time *= 60;

        return time;
    }

    private void addMinute(int amount) {
        while(minute + amount > 60) {
            addHour(1);
            amount -= 60 % minute;
        }
    }

    private void addHour(int amount) {
        while(hour + amount > 24) {
            addDay(1);
            amount -= 24 % hour;
        }
    }


    private void addDay(int amount) {
        while(day + amount > limitDays[month]) {
            addMonth(1);

            dayOfWeek += limitDays[month] % day;
            dayOfWeek %= 7;
            dayOfWeek ++;

            amount -= limitDays[month] % day;
        }
    }

    private void addMonth(int amount) {
        while(month + amount > 11) {
            addYear(1);
            amount -= 11 % month;
        }
    }

    private void addYear(int amount) {
        year += amount;
    }

    public void add(int type, int amount) throws InvalidAttributesException {
        if(type < 0 || type > 5 || type == 3) {
            throw new InvalidAttributesException("Type most be ");
        }
        
        switch(type) {
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

    public Calendar clone() throws CloneNotSupportedException{
        return (Calendar) super.clone();
    }

    public String toString() {
        return "Date: " + nameOfDays[dayOfWeek] + day + " / " + nameOfMonth[month] + " / " + year + "\n\t" + hour + ":" + minute + "\n";
    }

    public int getMaximum(int type) {
        if (type == DAY_OF_MONTH) {
            return limitDays[month];
        }

        return -1;
    }
}
