package interfaces.user;

import funcionabilities.functional_aids.calendar.Calendar;

import javax.naming.directory.InvalidAttributesException;
import java.util.Scanner;

class UtilsMain {
    private final static String start = "\n\nNew interation!\n" +
            "\t0: addEmployee\n"+
            "\t1: createPersonalPayment\n"+
            "\t2: printState\n"+
            "\t3: processEmployeeDetail\n"+
            "\t4: processPointCard\n"+
            "\t5: processSale\n"+
            "\t6: processServiceChage\n"+
            "\t7: removeEmployee\n"+
            "\t8: runPayroll\n"+
            "\t9: setPersonalPayment\n"+
            "\t10: undoRedo\n" +
            "\t11: exit\n";

    private final static String identificaton = "\t0: ID\n"
            + "\t1: Name\n"
            + "\n\tYour answer: ";

    private static final Scanner scan = new Scanner(System.in);

    static Object readEntries(Class type) {
        if(type.equals(Integer.class)) {
            while(true) {
                System.out.print("\n\t\tYour answer: ");
                if(scan.hasNextInt()) return scan.nextInt();
                else {
                    scan.next();
                    System.out.println("\n\t\tPlease enter with integer\n");
                }
            }
        }
        else if(type.equals(Double.class)) {
            while(true) {
                System.out.print("\n\t\tYour answer: ");
                if(scan.hasNextDouble()) return scan.nextDouble();
                else{
                    scan.next();
                    System.out.println("\n\t\tPlease enter with Double\n");
                }
            }
        }

        else return null;
    }

    static int readEntries(int i, int i1) {
        int read = (int) readEntries(Integer.class);

        if(read < i || read > i1) {
            return readEntries(i, i1);
        }
        else {
            return read;
        }
    }

    static Calendar getDate() {

        System.out.print("\nEnter with data(Only in numbers):");

        System.out.print("\t\tDay of Week (De 1 a 7): ");
        int day_of_week = readEntries(1,7);

        System.out.print("\t\tDay of Month: ");
        int day_of_month = readEntries(1,31);

        System.out.print("\t\tMonth: ");
        int month = readEntries(1,11);

        System.out.print("\t\tYear: ");
        int year = readEntries(0, Integer.MAX_VALUE);

        System.out.print("\t\tHour of day: ");
        int hour = readEntries(0,24);

        System.out.print("\t\tMinute: ");
        int minute = readEntries(0,60);

        try {
            return new Calendar(year, month, day_of_month, day_of_week, hour, minute);
        } catch (InvalidAttributesException e) {
            System.out.println("Error in your date, please enter that again!\n");
            return getDate();
        }
    }

    static void printIntro() {
        System.out.println(start);
    }

    static void printIdentification() {
        System.out.println(identificaton);
    }

    static String takeString() {
        for(int i = 0; i < 0; i++) scan.nextLine();
        return scan.nextLine();
    }
}
