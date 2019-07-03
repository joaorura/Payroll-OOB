package interfaces.user;

import funcionabilities.Commisioned;
import funcionabilities.Employee;
import funcionabilities.Hourly;
import funcionabilities.Salaried;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.transactions.BankAcount;
import interfaces.system.Payroll;
import interfaces.SystemSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class CreateElements {
    private static Object apresentType(Map<Class, Integer[]> aMap) {
        System.out.println("\n\tChose the type: \n");
        for (Map.Entry<Class, Integer[]> entry : aMap.entrySet()) {
            Class key = entry.getKey();
            Integer[] value = entry.getValue();

            if(value[0] < 0) continue;
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

    static boolean readEvery(String camp) {
        System.out.println("\nYou want to change the field " + camp + ": " +
                "\n\t0: No" +
                "\n\t1: Yes");
        return UtilsMain.readEntries(0,1) == 1;
    }

    static void identificatonProcess(Employee emp, ArrayList<Object> param) {
        /* param[0] são os atributos primitivos das possiveis classes de empregados*/
        boolean check = emp != null;

        param.add(null);

        if(check && readEvery("name")) {
            System.out.print("\tName: ");
            UtilsMain.takeString();
            param.add(UtilsMain.takeString());
        }
        else param.add(emp.getName());

        if(check && readEvery("adress")) {
            System.out.print("\tAdress: ");
            param.add(UtilsMain.takeString());
        }
        else param.add(emp.getAdress());

        if(check) param.add(Payroll.getDefault().nextId());

        if(check && readEvery("type of employee")) {
            System.out.println("\nType: \n" +
                    "\t0: Hourly\n" +
                    "\t1: Salaried\n" +
                    "\t2 : Commissioned\n");

            int aux = UtilsMain.readEntries(0,2);

            switch (aux) {
                case 0:
                    param.set(0, Hourly.class);

                    System.out.print("\nMaximun hours to work: ");
                    param.add(UtilsMain.readEntries(Integer.class));

                    System.out.print("\nTax to over work: ");
                    param.add(UtilsMain.readEntries(Double.class));

                    System.out.println("\nRatio Hour woked: ");
                    param.add(UtilsMain.readEntries(Double.class));

                    break;

                case 1:
                    param.set(0, Salaried.class);

                    System.out.println("\n\nSalary: ");
                    param.add(UtilsMain.readEntries(Double.class));

                    break;

                case 2:
                    param.set(0, Commisioned.class);

                    System.out.println("\n\nSalary: ");
                    param.add(UtilsMain.readEntries(Double.class));

                    System.out.println("\n\nRatio sales");
                    param.add(UtilsMain.readEntries(Double.class));

                    break;
            }
        }
        else {
            if(emp instanceof Hourly) {
                param.set(0, Hourly.class);
                Hourly h = (Hourly) emp;
                param.add(h.getMaxWorkHours());
                param.add(h.getOverWork());
                param.add(h.geRatioWork());

            } else if(emp instanceof Salaried) {
                param.set(0, Salaried.class);
                Salaried h = (Salaried) emp;
                param.add(h.getSalary());
            }
            else {
                param.set(0, Commisioned.class);
                Commisioned c = (Commisioned) emp;
                param.add(c.getRatioSales());
            }
        }

    }

    static void syndicateProcess(Employee emp, List<Object> param) {
        boolean check = emp != null;

        if(!(check && readEvery("Syndicate"))) {
            System.out.println("\nSyndicate: \n" +
                    "\tIt has: \n" +
                    "\t\t0: No\n" +
                    "\t\t1: Yes\n");
        }
        else {
            System.out.println("Change");
        }

        int aux= UtilsMain.readEntries(0,1);

        /*param[1] é a lista que contem os parametros referidos ao Sindicato*/
        if(aux == 0) {
            param.add(Class.class);
        }
        else {
            param.add(apresentType(SystemSettings.TYPE_SYNDICATES));

            if (SystemSettings.TYPE_SYNDICATES.get(param.get(0))[0] == 0) {
                if(check && readEvery("identification of syndicate")) {
                    System.out.print("\nIdentification of syndicate: ");
                    UtilsMain.takeString();
                    param.add(UtilsMain.takeString());
                }
                else {
                    param.add(emp.getSyndicate().getIndetification());
                }

                if(check && readEvery("month fee of syndicate")) {
                    System.out.print("Monthly fee of syndicate: ");
                    param.add(UtilsMain.readEntries(Double.class));
                }
                else {
                    param.add(emp.getSyndicate().getMonthlyFee());
                }
            }
            else {
                throw new IllegalStateException("Unexpected value: " + SystemSettings.TYPE_SYNDICATES.get(param.get(0))[0]);
            }
        }
    }

    static void methodProcess(String name, String adress, List<Object> param) {

        System.out.println("\nMethods Payment: ");
        param.add(apresentType(SystemSettings.TYPE_METHODS_PAYMENTS));

        System.out.println("\nBank of source:\n" +
                "\t0: Bank of Company\n" +
                "\t1: New Bank");
        int aux = UtilsMain.readEntries(0,1);

        if(aux == 0) {
            param.add(SystemSettings.ACOUNT);
        }
        else {

            System.out.println("\tSource acount:");
            String acount = UtilsMain.takeString();

            System.out.println("\tIdentification (CPF or CNPJ): ");
            String identification = UtilsMain.takeString();

            assert name != null;
            param.add(new BankAcount(name.concat(""), acount, identification));
        }

        param.add((double) -1);
        switch (SystemSettings.TYPE_METHODS_PAYMENTS.get(param.get(0))[0]) {
            case 0:
                System.out.print("Acount number to transactions: ");
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


    static void typeProcess(List<Object> param) {
        System.out.println("\n\nType Payment: \n" +
                "\tYou want use predefintion: \n" +
                "\t\t0: No\n" +
                "\t\t1: Yes\n");
        int aux = UtilsMain.readEntries(0,1);

        if(aux == 0){
            param.add(apresentType(SystemSettings.TYPE_PAYMENTS));

            if (SystemSettings.TYPE_PAYMENTS.get(param.get(0))[0] == 0) {
                param.add(Payroll.getDefault().getActualCalendar().clone());
                System.out.print("Day of week to payment [1 to 7 and -1, this represent a day of finaly of month: ");
                param.add(UtilsMain.readEntries(-1,7));

                if((int) param.get(2) != -1) {
                    System.out.print("Amount of weeks of interval: ");
                    param.add(UtilsMain.readEntries(0, Integer.MAX_VALUE));
                }
                else param.add(0);

                System.out.println("Amount of months of interval: ");
                param.add(UtilsMain.readEntries(0, Integer.MAX_VALUE));
            } else {
                throw new IllegalStateException("Unexpected value: " + SystemSettings.TYPE_PAYMENTS.get(param.get(0))[0]);
            }
        }
        else {
            System.out.println("\n\t\tPredefinitions: \n");

            aux = SystemSettings.DEFAULT_TYPESPAYMENTS.length;
            for(int i = 0; i < aux; i ++) {
                System.out.println("\t\t" + i + ": " + SystemSettings.DEFAULT_TYPESPAYMENTS[i].toString());
            }

            aux = UtilsMain.readEntries(0, aux - 1);
            param.add(ITypePayments.class);
            param.add(SystemSettings.DEFAULT_TYPESPAYMENTS[aux].clone());

            ((ITypePayments) param.get(1)).setLastPayment(Payroll.getDefault().getActualCalendar().clone());
        }

    }

    static void pointsProcess(List<Object> param) {
        System.out.println("Points: ");
        param.add(apresentType(SystemSettings.TYPE_POINTS));

        if (SystemSettings.TYPE_POINTS.get(param.get(0))[0] == 0) {
            System.out.println("Entre com um caractere que escolherá a administração do tempo" +
                    "\n\t(0: Hourly | 1: Month | 2: Daily\n\t");
            param.add(UtilsMain.readEntries(Integer.class));
        } else {
            throw new IllegalStateException("Unexpected value: " + SystemSettings.TYPE_POINTS.get(param.get(0))[0]);
        }
    }
}
