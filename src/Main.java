import funcionabilities.Commisioned;
import funcionabilities.Employee;
import funcionabilities.Hourly;
import funcionabilities.Salaried;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.transactions.BankAcount;

import java.util.*;

public class Main {
    private final static String start = "\n\nNova interação!"
            + "\n\nQual função você deseja realizar\n"
            + "\t0: Adição de um empregado\n"
            + "\t1: Remoção de um empregado\n"
            + "\t2: Lançar um Cartão de Ponto\n"
            + "\t3: Lançar um Resultado Venda\n"
            + "\t4: Lançar uma taxa de serviço\n"
            + "\t5: Alterar detalhes de um empregado\n"
            + "\t6: Rodar a folha de pagamento para hoje\n"
            + "\t7: Undo ou redo\n"
            + "\t8: Alterar agenda de pagamento de um empregado\n"
            + "\t9: Criação de Novas Agendas de Pagamento\n"
            + "\t10: Listar estado\n"
            + "\t11: Sair do programa";

    private final static String identificaton = "\t0: ID\n"
                                            + "\t1: Name\n"
                                            + "\n\tYour answer: ";

    private static final Scanner scan = new Scanner(System.in);

    private static GregorianCalendar getDate() {
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

    private static Object readEntries(Class type) {
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

    private static int readEntries(int i, int i1) {
        int read = (int) readEntries(Integer.class);

        if(read < i || read > i1) {
            return readEntries(i, i1);
        }
        else {
            return read;
        }
    }

    private static Object apresentType(Map<Class, Integer[]> aMap) {
        System.out.println("\n\tChose the type: \n");
        for (Map.Entry<Class, Integer[]> entry : aMap.entrySet()) {
            Class key = entry.getKey();
            Integer[] value = entry.getValue();

            if(value[0] < 0) continue;
            System.out.println("\t\t" + value[0] + ": " + key.descriptorString() + " ; " + value[1] + "\n");
        }

        System.out.println("\n\n\tYour Answer: ");
        int aux = readEntries(0, aMap.size() - 1);

        for (Map.Entry<Class, Integer[]> entry : aMap.entrySet()) {
            Class key = entry.getKey();
            Integer[] value = entry.getValue();
            if (value[0] == aux) return key;
        }

        throw new Error();
    }

    private static void syndicateProcess(Map<Class, Integer[]> aMap, List<Object> param) {
        System.out.println("Syndicate: \n" +
                "\tIt has: \n" +
                "\t\t0: No\n" +
                "\t\t1: Yes\n");

        int aux= readEntries(0,1);

        /*param[1] é a lista que contem os parametros referidos ao Sindicato*/
        if(aux == 0) {
            param.add(Class.class);
        }
        else {
            aMap = SystemSettings.TYPE_SYNDICATES;
            param.add(apresentType(aMap));

            if (aMap.get(param.get(0))[0] == 0) {
                System.out.print("\nIndentification of syndicate: ");
                scan.nextLine();
                param.add(scan.nextLine());

                System.out.print("Monthly fee of syndicate: ");
                param.add(readEntries(Double.class));
            }
            else {
                throw new IllegalStateException("Unexpected value: " + aMap.get(param.get(0))[0]);
            }
        }
    }

    private static void methodProcess(String name, String adress, Map<Class, Integer[]> aMap, List<Object> param) {
        System.out.println("\nMethods Payment: ");
        param.add(apresentType(aMap));

        System.out.println("\nBank of source:\n" +
                "\t0: Bank of Company\n" +
                "\t1: New Bank");
        int aux = readEntries(0,1);

        if(aux == 0) {
            param.add(SystemSettings.ACOUNT);
        }
        else {
            System.out.println("\tSource acount:");
            String acount = scan.nextLine();

            System.out.println("\tIdentification (CPF or CNPJ): ");
            String identification = scan.nextLine();

            assert name != null;
            param.add(new BankAcount(name.concat(""), acount, identification));
        }

        param.add((double) -1);
        switch (aMap.get(param.get(0))[0]) {
            case 0:
                System.out.print("Acount number to transactions: ");
                param.add(scan.nextLine());

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
                throw new IllegalStateException("Unexpected value: " + aMap.get(param.get(0))[0]);
        }
    }


    private static void typeProcess(Map<Class, Integer[]> aMap, List<Object> param) {
        System.out.println("Type Payment: \n" +
                "\tYou want use predefintion: \n" +
                "\t\t0: No\n" +
                "\t\t1: Yes\n");
        int aux = readEntries(0,1);

        if(aux == 0){
            param.add(apresentType(aMap));

            if (aMap.get(param.get(0))[0] == 0) {
                param.add(Payroll.getDefault().getActualCalendar().clone());
                System.out.print("Day of week to payment [1 to 7 and -1, this represent a day of finaly of month: ");
                param.add(readEntries(-1,7));

                if((int) param.get(2) != -1) {
                    System.out.print("Amount of weeks of interval: ");
                    param.add(readEntries(0, Integer.MAX_VALUE));
                }
                else param.add(0);

                System.out.println("Amount of months of interval: ");
                param.add(readEntries(0, Integer.MAX_VALUE));
            } else {
                throw new IllegalStateException("Unexpected value: " + aMap.get(param.get(0))[0]);
            }
        }
        else {
            System.out.println("\n\t\tPredefinitions: \n");

            aux = SystemSettings.DEFAULT_TYPESPAYMENTS.length;
            for(int i = 0; i < aux; i ++) {
                System.out.println("\t\t" + i + ": " + SystemSettings.DEFAULT_TYPESPAYMENTS[i].toString());
            }

            aux = readEntries(0, aux - 1);
            param.add(ITypePayments.class);
            try {
                param.add(SystemSettings.DEFAULT_TYPESPAYMENTS[aux].clone());

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            ((ITypePayments) param.get(1)).setLastPayment(Payroll.getDefault().getActualCalendar().clone());
        }

    }

    private static void pointsProcess(Map<Class,Integer[]> aMap,  List<Object> param) {
        System.out.println("Points: ");
        param.add(apresentType(aMap));

        if (aMap.get(param.get(0))[0] == 0) {
            System.out.println("Entre com um caractere que escolherá a administração do tempo" +
                    "\n\t(0: Hourly | 1: Month | 2: Daily\n\t");
            param.add(readEntries(Integer.class));
        } else {
            throw new IllegalStateException("Unexpected value: " + aMap.get(param.get(0))[0]);
        }
    }

    private static void identificatonProcess(Map<Class, Integer[]> aMap, ArrayList<Object> param) {
        /* param[0] são os atributos primitivos das possiveis classes de empregados*/

        param.add(null);

        System.out.print("\tName: ");
        scan.nextLine();
        String name = scan.nextLine();
        param.add(name);

        System.out.print("\tAdress: ");
        String adress = scan.nextLine();
        param.add(adress);

        param.add(Payroll.getDefault().nextId());

        System.out.println("\nType: \n" +
                "\t0: Hourly\n" +
                "\t1: Salaried\n" +
                "\t2 : Commissioned\n");

        int aux = readEntries(0,2);

        switch (aux) {
            case 0:
                param.set(0, Hourly.class);

                System.out.print("\nMaximun hours to work: ");
                param.add(readEntries(Integer.class));

                System.out.print("\nTax to over work: ");
                param.add(readEntries(Double.class));

                System.out.println("\nRatio Hour woked: ");
                param.add(readEntries(Double.class));

                break;

            case 1:
                param.set(0, Salaried.class);

                System.out.println("\n\nSalary: ");
                param.add(readEntries(Double.class));

                break;

            case 2:
                param.set(0, Commisioned.class);

                System.out.println("\n\nRatio sales");
                param.add(readEntries(Double.class));

                break;
        }
    }

    private static Employee addEmployee() {
        System.out.println("Add employee!\n");
        Payroll pay = Payroll.getDefault();
        ArrayList<Object>[] param = new ArrayList[5];
        for(int i = 0; i < 5; i ++) param[i] = new ArrayList<>();
        int id = pay.nextId();

        Map<Class, Integer[]> aMap = null;

        identificatonProcess(SystemSettings.TYPE_EMPLOYEES, param[0]);
        syndicateProcess(SystemSettings.TYPE_SYNDICATES, param[1]);
        methodProcess((String) param[0].get(2), (String) param[0].get(3),
                SystemSettings.TYPE_METHODS_PAYMENTS, param[2]);
        typeProcess(SystemSettings.TYPE_PAYMENTS, param[3]);
        pointsProcess(SystemSettings.TYPE_POINTS, param[1]);

        return pay.addEmployee(param);
    }

    private static boolean processEntries(int input) {
        System.out.println("\nStarting the operation ...\n");
        Payroll pay = Payroll.getDefault();
        Employee emp = null;
        int type_id = -1;
        int id = -1;
        int aux;

        String name = null;
        if(input != 11) {
            pay.backup(true);

            System.out.println(identificaton);
            type_id = readEntries(0,1);

            if(type_id == 0) id = readEntries(0, Integer.MAX_VALUE);
            else name = scan.nextLine();
        }
        boolean check = false;
        switch (input) {
            case 0:
                emp = addEmployee();
                System.out.println("Add : " + emp.toString());
                return true;

            case 1:
                System.out.println("\nRemove Employee!\n");

                if(type_id == 0) emp = emp = pay.removeEmployee(id);
                else emp = pay.removeEmployee(name);

                if(emp == null) return false;
                else {
                    System.out.println("Removed: \n\t" + emp.toString());
                    return true;
                }

            case 2:
                System.out.println("\nProcess Point Card!\n");
                System.out.println(identificaton);

                System.out.print("Start of turn: ");
                GregorianCalendar start = getDate();

                System.out.println("End of turn: ");
                GregorianCalendar end = getDate();
                if(type_id == 0) return pay.processPointCard(id, start, start);
                else return pay.processPointCard(name, start, start);

            case 3:
                System.out.println("\nProcess Sale Result\n");
                System.out.println("\n\tName of product: ");
                String name_product = scan.nextLine();

                System.out.println("\n\tValue of product: ");
                Double value_product = (Double) readEntries(Double.class);

                System.out.println(identificaton);

                if(value_product == null) return false;
                if(type_id == 0) {
                    try {
                        return pay.processSaleResult(id, name_product, value_product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        return pay.processSaleResult(name, name_product, value_product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            case 4:
                System.out.println("You desire retire or add services: \n" +
                        "\t0: Add\n" +
                        "\t1: Remove");

                aux = readEntries(0,1);
                boolean type;
                if(aux == 0) type = true;
                else type = false;

                name_product = scan.nextLine();
                value_product = (Double) readEntries(Double.class);

                if(type_id == 0) pay.processServiceChange(type, id, name_product, value_product);
                else pay.processServiceChange(type, name, name_product, value_product);

                break;

            case 5:
                check = pay.processEmployeeDetail();
                break;

            case 6:
                check = pay.runPayrollToday();
                break;

            case 7:
                System.out.println("Você deseja:");
                System.out.println("\t0: Desfazer");
                System.out.println("\t1: Refazer");
                if (readEntries(0, 1) == 0) check = pay.undo();
                else check = pay.redo();

                break;

            case 8:
                ArrayList<Object> param = new ArrayList<>();
                typeProcess(SystemSettings.TYPE_PAYMENTS, param);
                pay.setEmployerPaymentSchedule(null);
                break;

            case 9:
                check = pay.createEmployerPaymentSchedule();
                break;

            case 10:
                System.out.println("State: \n\t" + pay.toString());
                check = true;
                 break;
        }

        return check;
    }

    public static void main (String[] args) {
        System.out.println("Inicializando o sistema\n" +
                "\tInicializando configurações:\n");
        Payroll.getDefault().configurations(getDate());

        int input;
        while (true) {
            System.out.println(start);
            input = readEntries(0,11);

                if(input == 11) return;
                else if (!processEntries(input)) {
                    System.out.println("A sua operação falhou, o sistema não conlcluiu a sua ação. Por favor " +
                            "realize-a novamente!");
                }
        }
    }
}
