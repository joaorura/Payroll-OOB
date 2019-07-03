package interfaces.system;

import funcionabilities.Commisioned;
import funcionabilities.Employee;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.calendar.Calendar;
import interfaces.SystemSettings;
import interfaces.user.CreateElements;
import interfaces.user.UtilsMain;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.Iterator;

public class Payroll implements Cloneable {
    private static final Restore backup = new Restore();
    private static Payroll pay_default = new Payroll();
    private ArrayList<Employee> employees;
    private Calendar actualCalendar;

    private Payroll() {
        this.employees = new ArrayList<>();
        this.actualCalendar = null;
    }

    public static Payroll getDefault() {
        return pay_default;
    }

    public Employee searchEmployee(int id) {
        try {
            return employees.get(id);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int searchEmployee(String name) {
        Iterator<Employee> iterable = employees.iterator();

        int i = 0;
        while (iterable.hasNext()) {
            if (iterable.next().getName().equals(name)) break;
            i++;
        }

        if (!iterable.hasNext()) return -1;
        else return i;
    }

    public void configurations(Calendar day) {
        this.actualCalendar = day;
    }

    public int nextId() {
        int i = 0;
        for (Employee aux : employees) {
            if (aux == null) break;
            else i++;
        }

        return i;
    }

    public Employee addEmployee(ArrayList<ArrayList<Object>> paramater) throws InvalidAttributesException, CloneNotSupportedException {
        Employee item = UtilsPayroll.processEmployee(paramater);

        if (item.getId() == employees.size()) {
            employees.add(item);
        } else {
            employees.set(item.getId(), item);
        }


        return item;
    }

    public Employee removeEmployee(int id) {
        if (id > employees.size() || id < 0)
            return null;
        else
            return employees.set(id, null);
    }


    public void processPointCard(int id, Calendar start, Calendar end) {
        Employee emp = searchEmployee(id);
        emp.getWorker().markPoint(start, end);
    }

    public boolean undo() {
        Payroll pay = backup.undo();
        if (pay == null) {
            return false;
        }

        Payroll.pay_default = pay;

        return true;
    }

    public boolean redo() {
        Payroll pay = backup.redo();
        if (pay == null) {
            return false;
        }

        Payroll.pay_default = pay;
        return true;
    }

    public void processSaleResult(int id, String name, double value) throws Error {
        Employee item = employees.get(id);

        if (item == null) throw new Error("Employee can't find");
        else if (item instanceof Commisioned) {
            ((Commisioned) item).getSales().addProduct(name, value);
        } else {
            throw new Error("The eploye must a be a Comissioned");
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) != null)
                str.append(i).append(": ").append(employees.get(i).toString()).append("\n");
        }

        return str.toString();
    }

    public void processServiceChange(boolean type, int id, String name_product, double value) {
        Employee emp = employees.get(id);

        if (type) {
            emp.getDebts().addDebt(name_product, value);
        } else {
            emp.getDebts().removeDebt(name_product);
        }
    }

    public void runPayrollToday() throws InvalidAttributesException {
        System.out.println(getActualCalendar().toString());

        for (int i = 0; i < 1440; i++) {

            try {
                for (Employee employee : employees) {
                    if (employee.getPersonalIPayment().checkItsDay(actualCalendar)) {
                        employee.attMoney();
                        System.out.println(employee.getName() + "\n" + employee.getMethodPayment().toString());
                    }
                }
            }
            catch (NullPointerException e) {
                System.out.println("Without employees\n");
                return;
            }


            actualCalendar.add(Calendar.MINUTE, 1);
        }
    }

    public void createEmployeePaymentSchedule() {
        ArrayList<Object> param = new ArrayList<>();
        try {
            CreateElements.typeProcess(true, param);
        } catch (InvalidAttributesException e) {
            e.printStackTrace();
        }

        try {
            PaymentBills pb = UtilsPayroll.createTypePayment(param);
            SystemSettings.DEFAULT_TYPE_PAYMENTS.add(pb);

        } catch (CloneNotSupportedException | InvalidAttributesException e) {
            throw new RuntimeException();
        }

    }

    public void backup(boolean type) {
        backup.backup(this, type);
    }

    public Calendar getActualCalendar() {
        return actualCalendar;
    }

    public void changeEmployee(int id, ArrayList<ArrayList<Object>> change) throws CloneNotSupportedException, InvalidAttributesException {
        Employee item = UtilsPayroll.processEmployee(change);
        employees.set(id, item);
    }

    public void setEmployeeSchedule(int id) throws CloneNotSupportedException {
        int i = 0;
        for (PaymentBills aux : SystemSettings.DEFAULT_TYPE_PAYMENTS) {
            System.out.println(i + ": " + aux.toString());
            i++;
        }

        int aux = UtilsMain.readEntries(0, i - 1);
        System.out.println("\n");

        PaymentBills type = SystemSettings.DEFAULT_TYPE_PAYMENTS.get(aux).clone();
        searchEmployee(id).setPersonalIPayment(type);
    }

    public Payroll clone() throws CloneNotSupportedException {
        Payroll p = (Payroll) super.clone();
        p.employees = new ArrayList<>();

        try {
            for (Employee employee : employees) {
                p.employees.add(employee.clone());
            }
        }
        catch (NullPointerException e) {
            p.employees = new ArrayList<>();
        }


        p.actualCalendar = actualCalendar.clone();
        return p;
    }
}