package interfaces.user;

import funcionabilities.Employee;
import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.calendar.Calendar;
import funcionabilities.functional_aids.calendar.PointCalendar;
import interfaces.system.Payroll;

import javax.naming.directory.InvalidAttributesException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class UtilsMain {
    private final static String start = "\n\nNew interation!\n" +
            "\t0: addEmployee\n" +
            "\t1: printState\n" +
            "\t2: processEmployeeDetail\n" +
            "\t3: processPointCard\n" +
            "\t4: processSale\n" +
            "\t5: processServiceChange\n" +
            "\t6: removeEmployee\n" +
            "\t7: runPayroll\n" +
            "\t8: setPersonalPayment\n" +
            "\t9: undoRedo\n" +
            "\t10: exit\n";

    private final static String identificaton = "\t0: ID\n"
            + "\t1: Name\n"
            + "\n\tYour answer: ";

    private static final Scanner scan = new Scanner(System.in);

    static boolean processEntries(int input) {
        System.out.println("\nStarting the operation ...\n");
        Payroll pay = Payroll.getDefault();
        int type_id;
        int id = -1;

        if (input != 9 && input != 1) {
            if (input != 0 && input != 7 && input != 8) {
                UtilsMain.printIdentification();
                type_id = readEntries(0, 1);

                if (type_id == 0) {
                    System.out.print("Identifcation: ");
                    id = readEntries(0, Integer.MAX_VALUE);
                } else {
                    System.out.print("Name: ");
                    id = pay.searchEmployee(UtilsMain.takeString());
                }

                if (pay.searchEmployee(id) == null) {
                    System.out.println("Employee not founded");
                    return false;
                }
            }

            pay.backup(true);
        }

        FuncionabilitiesInter.att(id);
        Object ret = null;
        try {
            ret = FuncionabilitiesInter.funcionabilities.get(input).invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (ret != null) {
            if (ret instanceof Boolean) return (boolean) ret;
            else return true;
        } else return true;
    }

    static Object readEntries(Class type) {
        if (type.equals(Integer.class)) {
            while (true) {
                System.out.print("\n\t\tYour answer: ");
                if (scan.hasNextInt()) return scan.nextInt();
                else {
                    scan.next();
                    System.out.println("\n\t\tPlease enter with integer\n");
                }
            }
        } else if (type.equals(Double.class)) {
            while (true) {
                System.out.print("\n\t\tYour answer: ");
                if (scan.hasNextDouble()) return scan.nextDouble();
                else {
                    scan.next();
                    System.out.println("\n\t\tPlease enter with Double\n");
                }
            }
        } else return null;
    }

    static ArrayList<ArrayList<Object>> getDatas(Employee emp) throws InvalidAttributesException {
        ArrayList<ArrayList<Object>> param = new ArrayList<>();
        ArrayList<Object> auxOb;

        for (int i = 0; i < 5; i++) {
            param.add(new ArrayList<>());
        }

        boolean check = emp != null;
        CreateElements.identificatonProcess(emp, param.get(0));

        if (CreateElements.canChange(check, "Syndicate")) {
            CreateElements.syndicateProcess(param.get(1));
        } else {
            Syndicate synd = emp.getSyndicate();
            auxOb = param.get(1);

            if (synd == null) {
                auxOb.add(Class.class);
            } else {
                auxOb.add(synd.getIndetification());
                auxOb.add(synd.getMonthlyFee());
            }
        }

        CreateElements.methodProcess((String) param.get(0).get(1), (String) param.get(0).get(2), param.get(2));

        CreateElements.typeProcess(false, param.get(3));

        param.get(4).add(PointCalendar.class);

        return param;
    }

    public static int readEntries(int i, int i1) {
        int read = (int) readEntries(Integer.class);

        if (read < i || read > i1) {
            return readEntries(i, i1);
        } else {
            return read;
        }
    }

    static Calendar getDate() {

        System.out.print("\nEnter with data(Only in numbers):");

        System.out.print("\t\tDay of Week (De 1 a 7): ");
        int day_of_week = readEntries(1, 7);

        System.out.print("\t\tDay of Month: ");
        int day_of_month = readEntries(1, 31);

        System.out.print("\t\tMonth: ");
        int month = readEntries(1, 11);

        System.out.print("\t\tYear: ");
        int year = readEntries(0, Integer.MAX_VALUE);

        System.out.print("\t\tHour of day: ");
        int hour = readEntries(0, 24);

        System.out.print("\t\tMinute: ");
        int minute = readEntries(0, 60);

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
        return scan.nextLine();
    }
}
