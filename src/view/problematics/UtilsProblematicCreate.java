package view.problematics;

import model.problematics.PaymentBills;
import model.BankAccount;
import controller.Payroll;
import controller.EmployeeController;
import view.utils.UtilsSystem;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.List;

import static view.utils.UtilsSystem.readEntries;

public class UtilsProblematicCreate {
    public static boolean canChange(boolean check, String camp) {
        if (camp == null) throw new Error();
        if (!check) return true;

        System.out.println("You desire change the " + camp + ":\n" +
                "\t0: No\n" +
                "\t1: Yes");

        return readEntries(0, 1) == 1;
    }

    public static void syndicateProcess(List<Object> param) {
        System.out.println("\nSyndicate: \n" +
                "\tIt has: \n" +
                "\t\t0: No\n" +
                "\t\t1: Yes\n");

        int aux = readEntries(0, 1);

        if (aux == 0) {
            param.add(-1);
        } else {
            param.add(0);
            System.out.print("\nIdentification of syndicate: ");
            param.add(UtilsSystem.takeString());
            System.out.print("Monthly fee of syndicate: ");
            param.add(readEntries(Double.class));
        }
    }

    private static int presentMethods() {
        System.out.println("Choose payment method: ");
        System.out.println("\t0: Bank deposit.\n" +
                "\t1: Check delivered in hands.\n" +
                "\t2: Check sent via post office.\n");

        return readEntries(0,2);
    }

    public static void methodProcess(EmployeeController employeeControl, String name,
                                     String address, List<Object> param) {

        System.out.println("\nMethods Payment: ");
        param.add(presentMethods());

        System.out.println("\nBank of source:\n" +
                "\t0: Bank of Company\n" +
                "\t1: New Bank");
        int aux = readEntries(0, 1);

        if(aux == 0) {
            param.add(null);
        }
        else {
            System.out.println("\tSource account:");
            String account = UtilsSystem.takeString();

            System.out.println("\tIdentification (CPF or CNPJ): ");
            String identifier = UtilsSystem.takeString();

            assert name != null;
            param.add(new BankAccount(name.concat(""), account, identifier));
        }

        param.add(0.0);

        aux = (int) param.get(0);
        switch (aux) {
            case 0:
                System.out.print("\nAccount number to transactions: ");
                param.add(UtilsSystem.takeString());

                break;
            case 1:
                assert name != null;
                param.add(name.concat(""));
                param.add(employeeControl.nextId());
                break;
            case 2:
                assert address != null;
                param.add(name.concat(""));
                param.add(address.concat(""));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + aux + " when collecting payment method data " +
                        "from an employee");
        }
    }

    public static void typeProcess(Payroll pay, boolean type, List<Object> param) throws InvalidAttributesException {
        System.out.println("\n\nType Payment: \n" +
                "\tYou want use presets: \n" +
                "\t\t0: No\n" +
                "\t\t1: Yes\n");
        int aux = readEntries(0, 1);
        param.add(aux);

        if (type || aux == 0) {
            aux = (int) param.get(0);
            if (aux == 0) {
                try {
                    param.add(pay.getActualCalendar().clone());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException();
                }

                System.out.print("Day of week to payment [1 to 7 and -1, this represent a day of finally of month: ");
                param.add(readEntries(-1, 7));

                if ((int) param.get(2) != -1) {
                    System.out.print("Amount of weeks of interval: ");
                    param.add(readEntries(0, Integer.MAX_VALUE));
                } else param.add(0);

                System.out.println("Amount of months of interval: ");
                param.add(readEntries(0, Integer.MAX_VALUE));
            } else {
                throw new IllegalStateException("Unexpected value: " + aux);
            }
        } else {
            System.out.println("\n\t\tPresets: \n");

            ArrayList<PaymentBills> paymentList = pay.getDefaultTypePayments();
            aux = paymentList.size();
            for(int i = 0; i < aux; i++) {
                System.out.println("\t\t" + i + ": " + paymentList.get(i).toString());
            }

            aux = readEntries(0, aux - 1);
            try {
                PaymentBills paymentBills = paymentList.get(aux);
                param.add(paymentBills);
                paymentBills.setLastPayment(pay.getActualCalendar().clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException();
            }
        }
    }
}
