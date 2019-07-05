package interfaces.user;

import funcionabilities.Employee;
import funcionabilities.functional_aids.calendar.Calendar;
import interfaces.system.Payroll;

import javax.naming.directory.InvalidAttributesException;
import java.lang.reflect.Method;
import java.util.*;

import static interfaces.user.UtilsMain.readEntries;

class FuncionabilitiesInter {
    public static final Map<Integer, Method> funcionabilities = new HashMap<>();
    static {
        Method[] methods = FuncionabilitiesInter.class.getDeclaredMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        int j = 0;
        for (Method method : methods) {
            if (method.getName().equals("att")) continue;
            System.out.println(method.getName());
            funcionabilities.put(j, method);
            j++;
        }
    }

    private static Payroll pay = null;
    private static int type_id = -1;
    private static int id = -1;

    static void att(int type_id, int id) {
        pay = Payroll.getDefault();
        type_id = type_id;
        id = id;
    }

    static Employee addEmployee() {
        System.out.println("Add employee!\n");
        ArrayList<ArrayList<Object>> param = new ArrayList<>();

        for(int i = 0; i < 5; i ++) {param.add(new ArrayList<>());}

        CreateElements.identificatonProcess(param.get(0));
        CreateElements.syndicateProcess(param.get(1));
        CreateElements.methodProcess((String) param.get(0).get(1), (String) param.get(0).get(2), param.get(2));
        CreateElements.typeProcess(param.get(3));

        try {
            return pay.addEmployee(param);
        } catch (InvalidAttributesException | CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Employee removeEmployee() {
        System.out.println("\nRemove Employee!\n");
        Employee emp_aux = emp_aux = pay.removeEmployee(id);

        if(emp_aux == null) {
            return null;
        }
        else {
            System.out.println("Removed: \n\t" + emp_aux.toString());
            return emp_aux;
        }

    }

    static void processPointCard() {
        System.out.println("\nProcess Point Card!\n");

        System.out.print("Start of turn: ");
        Calendar start = UtilsMain.getDate();

        System.out.println("End of turn: ");
        Calendar end = UtilsMain.getDate();
        pay.processPointCard(id, start, start);
    }

    static void processSale() {
        System.out.println("\nProcess Sale Result\n");
        System.out.println("\n\tName of product: ");
        String name_product = UtilsMain.takeString();

        Double value_product;
        System.out.println("\n\tValue of product: ");
        do value_product = (Double) UtilsMain.readEntries(Double.class); while (value_product == null);

        try {
            pay.processSaleResult(id, name_product, value_product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void processServiceChange() {
        System.out.println("You desire retire or add services: \n" +
                "\t0: Add\n" +
                "\t1: Remove");

        int aux = UtilsMain.readEntries(0,1);
        boolean type = (aux == 0);

        String name_product = UtilsMain.takeString();
        Double value_product = (Double) UtilsMain.readEntries(Double.class);
        if(value_product == null) {
            throw new Error();
        }

        pay.processServiceChange(type, id, name_product, value_product);
    }

    static void processEmployeeDetail(){
        System.out.println("Changes of employee: ");
        pay.changeEmployee(id, addEmployee());
    }

    static boolean undoRedo() {
        System.out.println("Você deseja:");
        System.out.println("\t0: Desfazer");
        System.out.println("\t1: Refazer");
        if (readEntries(0, 1) == 0) return pay.undo();
        else return pay.redo();
    }

    static void setPersonalPayment() {
        System.out.println("Create Employee Payment Schedule / Set Employee Payment Schedule\n" +
                "\t0: Create a employee schedule" +
                "\t1: Set a employee schedule");

        if(UtilsMain.readEntries(0,1) == 0) pay.createEmployeePaymentSchedule();
        else {
            try {
                pay.setEmployeeSchedule(id);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    static void printState() {
        System.out.println("State: \n\t" + pay.toString());
    }

    static void runPayroll() {
        pay.runPayrollToday();
    }
}
