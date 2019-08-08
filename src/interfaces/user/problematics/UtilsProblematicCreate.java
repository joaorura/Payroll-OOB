package interfaces.user.problematics;

import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.transactions.BankAccount;
import interfaces.system.Payroll;
import interfaces.system.controlers.EmployeeController;
import interfaces.user.utils.UtilsSystem;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static interfaces.user.utils.UtilsSystem.readEntries;

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

        /*param[1] é a lista que contem os parametros referidos ao Sindicato*/
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

    private static int apresentMethods() {
        System.out.println("Escolha o método de pagamento: ");
        System.out.println("\t0: Deposito bancário.\n" +
                "\t1: Check entregue em mãoes.\n" +
                "\t2: Check enviado via correios.\n");

        return readEntries(0,2);
    }

    public static void methodProcess(EmployeeController employeeControll, String name,
                                     String address, List<Object> param) {

        System.out.println("\nMethods Payment: ");
        param.add(apresentMethods());

        System.out.println("\nBank of source:\n" +
                "\t0: Bank of Company\n" +
                "\t1: New Bank");
        int aux = readEntries(0, 1);

        if(aux == 0) {
            param.add(null);
        }
        else {
            System.out.println("\tSource acount:");
            String acount = UtilsSystem.takeString();

            System.out.println("\tIdentification (CPF or CNPJ): ");
            String identification = UtilsSystem.takeString();

            assert name != null;
            param.add(new BankAccount(name.concat(""), acount, identification));
        }

        param.add(0.0);

        aux = (int) param.get(0);
        switch (aux) {
            case 0:
                System.out.print("\nAccount number to transactions: ");
                UtilsSystem.takeString();
                param.add(UtilsSystem.takeString());

                break;
            case 1:
                assert name != null;
                param.add(name.concat(""));
                param.add(employeeControll.nextId());
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
                "\tYou want use predefintion: \n" +
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

                System.out.print("Day of week to payment [1 to 7 and -1, this represent a day of finaly of month: ");
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
            System.out.println("\n\t\tPredefinitions: \n");

            ArrayList<PaymentBills> paymentBills = pay.getDefaultTypePayments();
            aux = paymentBills.size();
            for(int i = 0; i < aux; i++) {
                System.out.println("\t\t" + i + ": " + paymentBills.get(i).toString());
            }

            aux = readEntries(0, aux - 1);
            try {
                PaymentBills paux = paymentBills.get(aux);
                param.add(paux);
                paux.setLastPayment(pay.getActualCalendar().clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException();
            }
        }
    }
}
