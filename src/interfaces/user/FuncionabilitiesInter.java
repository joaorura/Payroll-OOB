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
    private static Payroll pay = null;
    private static int id = -1;

    static {
        Method[] methods = FuncionabilitiesInter.class.getDeclaredMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        int j = 0;
        for (Method method : methods) {
            if (method.getName().equals("att")) continue;
            funcionabilities.put(j, method);
            j++;
        }
    }

    static void att(int ide) {
        pay = Payroll.getDefault();
        id = ide;
    }

    static Employee addEmployee() {
        System.out.println("Add employee!\n");
        ArrayList<ArrayList<Object>> param = null;
        try {
            param = UtilsMain.getDatas(null);
        } catch (InvalidAttributesException e) {
            e.printStackTrace();
        }

        try {
            return pay.addEmployee(param);
        } catch (InvalidAttributesException | CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void printState() {
        System.out.println("State: \n\t" + pay.toString());
    }

    static void processEmployeeDetail() {
        System.out.println("Changes of employee: ");
        Employee emp = pay.searchEmployee(id);
        ArrayList<ArrayList<Object>> param = null;
        try {
            param = UtilsMain.getDatas(emp);
        } catch (InvalidAttributesException e) {
            e.printStackTrace();
        }

        try {
            pay.changeEmployee(id, param);
        } catch (CloneNotSupportedException | InvalidAttributesException e) {
            e.printStackTrace();
        }
    }

    static void processPointCard() {
        System.out.println("\nProcess Point Card!\n");

        System.out.print("Start of turn: ");
        Calendar start = UtilsMain.getDate();

        System.out.println("End of turn: ");
        Calendar end = UtilsMain.getDate();

        pay.processPointCard(id, start, end);
    }

    static void processSale() {
        System.out.println("\nProcess Sale Result\ns");
        System.out.print("\n\tName of product: ");
        UtilsMain.takeString();
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

        int aux = UtilsMain.readEntries(0, 1);
        boolean type = (aux == 0);

        System.out.print("Name of product: ");
        UtilsMain.takeString();
        String name_product = UtilsMain.takeString();

        System.out.println("Value of product: ");
        Double value_product = (Double) UtilsMain.readEntries(Double.class);
        if (value_product == null) {
            throw new Error();
        }

        pay.processServiceChange(type, id, name_product, value_product);
    }

    static Employee removeEmployee() {
        System.out.println("\nRemove Employee!\n");
        Employee emp_aux = pay.removeEmployee(id);

        if (emp_aux == null) {
            return null;
        } else {
            System.out.println("Removed: \n\t" + emp_aux.toString());
            return emp_aux;
        }

    }

    static void runPayroll() {
        System.out.println("Enter the amount days will be processed: \n");
        int amount = UtilsMain.readEntries(0, Integer.MAX_VALUE);

        try {
            for (int i = 0; i < amount; i++) pay.runPayrollToday();
        } catch (InvalidAttributesException e) {
            e.printStackTrace();
        }
    }

    static void setPersonalPayment() {
        System.out.println("Create Employee Payment Schedule / Set Employee Payment Schedule\n" +
                "\t0: Create a employee schedule\n" +
                "\t1: Set a employee schedule\n");

        if (UtilsMain.readEntries(0, 1) == 0) pay.createEmployeePaymentSchedule();
        else {
            try {
                UtilsMain.printIdentification();

                if (readEntries(0, 1) == 0) {
                    id = (int) UtilsMain.readEntries(Integer.class);
                } else {
                    String name = UtilsMain.takeString();
                    id = pay.searchEmployee(name);
                }

                if (pay.searchEmployee(id) == null) {
                    throw new Error();
                }

                pay.setEmployeeSchedule(id);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

    }

    static boolean undoRedo() {
        System.out.println("Você deseja:");
        System.out.println("\t0: Desfazer");
        System.out.println("\t1: Refazer");
        if (readEntries(0, 1) == 0) return pay.undo();
        else return pay.redo();
    }
}
