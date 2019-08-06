package interfaces.user.problematics;

import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.transactions.BankAcount;
import interfaces.SystemSettings;
import interfaces.system.Payroll;
import interfaces.user.UtilsMain;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;
import java.util.Map;

public class UtilsProblematicCreate {
    private static Object apresentType(Map<Class, Integer[]> aMap) {
        System.out.println("\n\tChose the type: \n");
        for (Map.Entry<Class, Integer[]> entry : aMap.entrySet()) {
            Class key = entry.getKey();
            Integer[] value = entry.getValue();

            if (value[0] < 0) continue;
            System.out.println("\t\t" + value[0] + ": " + key.descriptorString() + " ; " + value[1] + "\n");
        }

        System.out.println("\n\n\tYour Answer: ");
        int aux = UtilsMain.readEntries(0, aMap.size() - 1);

        for (Map.Entry<Class, Integer[]> entry : aMap.entrySet()) {
            Class key = entry.getKey();
            Integer[] value = entry.getValue();
            if (value[0] == aux) return key;
        }

        throw new Error();
    }

    public static boolean canChange(boolean check, String camp) {
        if (camp == null) throw new Error();
        if (!check) return true;

        System.out.println("You desire change the " + camp + ":\n" +
                "\t0: No\n" +
                "\t1: Yes");

        return UtilsMain.readEntries(0, 1) == 1;
    }

    public static void syndicateProcess(List<Object> param) {

        System.out.println("\nSyndicate: \n" +
                "\tIt has: \n" +
                "\t\t0: No\n" +
                "\t\t1: Yes\n");

        int aux = UtilsMain.readEntries(0, 1);

        /*param[1] é a lista que contem os parametros referidos ao Sindicato*/
        if (aux == 0) {
            param.add(Class.class);
        } else {
            param.add(apresentType(SystemSettings.TYPE_SYNDICATES));

            if (SystemSettings.TYPE_SYNDICATES.get(param.get(0))[0] == 0) {
                System.out.print("\nIdentification of syndicate: ");
                param.add(UtilsMain.takeString());
                System.out.print("Monthly fee of syndicate: ");
                param.add(UtilsMain.readEntries(Double.class));
            } else {
                throw new IllegalStateException("Unexpected value: " + SystemSettings.TYPE_SYNDICATES.get(param.get(0))[0]);
            }
        }
    }

    public static void methodProcess(String name, String adress, List<Object> param) {

        System.out.println("\nMethods Payment: ");
        param.add(apresentType(SystemSettings.TYPE_METHODS_PAYMENTS));

        System.out.println("\nBank of source:\n" +
                "\t0: Bank of Company\n" +
                "\t1: New Bank");
        int aux = UtilsMain.readEntries(0, 1);

        if (aux == 0) {
            param.add(SystemSettings.ACOUNT);
        } else {
            System.out.println("\tSource acount:");
            String acount = UtilsMain.takeString();

            System.out.println("\tIdentification (CPF or CNPJ): ");
            String identification = UtilsMain.takeString();

            assert name != null;
            param.add(new BankAcount(name.concat(""), acount, identification));
        }

        param.add(0.0);
        switch (SystemSettings.TYPE_METHODS_PAYMENTS.get(param.get(0))[0]) {
            case 0:
                System.out.print("\nAcount number to transactions: ");
                UtilsMain.takeString();
                param.add(UtilsMain.takeString());

                break;
            case 1:
                assert name != null;
                param.add(name.concat(""));
                param.add(Payroll.getDefault().nextId());
                break;
            case 2:
                assert adress != null;
                param.add(name.concat(""));
                param.add(adress.concat(""));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + SystemSettings.TYPE_METHODS_PAYMENTS.get(param.get(0))[0]);
        }
    }

    public static void typeProcess(boolean type, List<Object> param) throws InvalidAttributesException {
        System.out.println("\n\nType Payment: \n" +
                "\tYou want use predefintion: \n" +
                "\t\t0: No\n" +
                "\t\t1: Yes\n");
        int aux = UtilsMain.readEntries(0, 1);

        if (type || aux == 0) {
            param.add(apresentType(SystemSettings.TYPE_PAYMENTS));

            if (SystemSettings.TYPE_PAYMENTS.get(param.get(0))[0] == 0) {
                try {
                    param.add(Payroll.getDefault().getActualCalendar().clone());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException();
                }

                System.out.print("Day of week to payment [1 to 7 and -1, this represent a day of finaly of month: ");
                param.add(UtilsMain.readEntries(-1, 7));

                if ((int) param.get(2) != -1) {
                    System.out.print("Amount of weeks of interval: ");
                    param.add(UtilsMain.readEntries(0, Integer.MAX_VALUE));
                } else param.add(0);

                System.out.println("Amount of months of interval: ");
                param.add(UtilsMain.readEntries(0, Integer.MAX_VALUE));
            } else {
                throw new IllegalStateException("Unexpected value: " + SystemSettings.TYPE_PAYMENTS.get(param.get(0))[0]);
            }
        } else {
            System.out.println("\n\t\tPredefinitions: \n");

            aux = SystemSettings.DEFAULT_TYPE_PAYMENTS.size();
            for (int i = 0; i < aux; i++) {
                System.out.println("\t\t" + i + ": " + SystemSettings.DEFAULT_TYPE_PAYMENTS.get(i).toString());
            }

            aux = UtilsMain.readEntries(0, aux - 1);
            param.add(Class.class);
            try {
                PaymentBills paux = SystemSettings.DEFAULT_TYPE_PAYMENTS.get(aux).clone();
                param.add(paux);
                paux.setLastPayment(Payroll.getDefault().getActualCalendar().clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException();
            }
        }
    }
}
