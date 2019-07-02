package interfaces.user;

import java.util.GregorianCalendar;
import java.util.Scanner;

class UtilsMain {
    private final static String start = "\n\nNew interation!\n" +
            "\t0: addEmployee\n"+
            "\t1: printState\n"+
            "\t2: processEmployeeDetail\n"+
            "\t3: processPointCard\n"+
            "\t4: processSale\n"+
            "\t5: processServiceChange\n"+
            "\t6: removeEmployee\n"+
            "\t7: runPayroll\n"+
            "\t8: setPersonalPayment\n"+
            "\t9: undoRedo\n" +
            "\t10: exit\n";

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

    static GregorianCalendar getDate() {
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance().clone();

        System.out.print("\nEnter with data(Only in numbers):");

        System.out.print("\t\tDay of Week (De 1 a 7): ");
        calendar.set(GregorianCalendar.DAY_OF_WEEK, readEntries(1,7));

        System.out.print("\t\tDay of Month: ");
        calendar.set(GregorianCalendar.DAY_OF_MONTH, readEntries(1,31));

        System.out.print("\t\tMonth: ");
        calendar.set(GregorianCalendar.MONTH, readEntries(0,11));

        System.out.print("\t\tYear: ");
        calendar.set(GregorianCalendar.YEAR, readEntries(0, calendar.getActualMaximum(GregorianCalendar.YEAR)));

        System.out.print("\t\tHour of day: ");
        calendar.set(GregorianCalendar.HOUR_OF_DAY, readEntries(0,23));

        System.out.print("\t\tMinute: ");
        calendar.set(GregorianCalendar.MINUTE, readEntries(0,59));

        return calendar;
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
