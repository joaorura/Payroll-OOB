package interfaces.user.utils;

import funcionabilities.functional_aids.transactions.BankAccount;

import java.util.Scanner;


public class UtilsSystem {
    private final static String start = "\n\nNew interation!\n" +
            "\t0: addEmployee\n" +
            "\t1: removeEmployee\n" +
            "\t2: processEmployeeDetail\n" +
            "\t3: processPointCard\n" +
            "\t4: processSale\n" +
            "\t5: processServiceChange\n" +
            "\t6: printState\n" +
            "\t7: runPayroll\n" +
            "\t8: setPersonalPayment\n" +
            "\t9: undoRedo\n" +
            "\t10: exit\n";

    private final static String identificaton = "\t0: ID\n"
            + "\t1: Name\n"
            + "\n\tYour answer: ";

    private static final Scanner scan = new Scanner(System.in);

    public static Object readEntries(Class type) {
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

    public static int readEntries(int i, int i1) {
        int read = (int) readEntries(Integer.class);

        if (read < i || read > i1) {
            return readEntries(i, i1);
        } else {
            return read;
        }
    }

    public static void printIntro() {
        System.out.println(start);
    }

    public static void printIdentification() {
        System.out.println(identificaton);
    }

    public static String takeString() {
        String str = scan.nextLine();

        while(str.equals("") || str.equals("\n")) {
            System.out.print("\nEnter again: ");
            str = scan.nextLine();
        }

        return str;
    }


    public static BankAccount getBank() {
        System.out.println("\n\nPor favor entre com a conta bancária da empresa:");

        System.out.print("Name of Company: ");
        String name = takeString();

        System.out.print("Account of Company: ");
        String sourceAcount = takeString();

        System.out.print("Identifier: ");
        String identifier = takeString();

        System.out.println("\n");

        return new BankAccount(name, sourceAcount, identifier);
    }
}
