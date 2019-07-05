package interfaces.system;

import funcionabilities.*;
import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.calendar.Calendar;
import funcionabilities.functional_aids.calendar.PointCalendar;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.transactions.*;
import interfaces.SystemSettings;
import interfaces.user.CreateElements;
import interfaces.user.UtilsMain;

import javax.naming.directory.InvalidAttributesException;
import java.util.*;

public class Payroll implements Cloneable{
    private ArrayList<Employee> employees;
    private static final Restore backup = new Restore();
    private ArrayList<IMethodsPayments> methodsPayments;
    private ArrayList<PaymentBills> typesPayments;
    private Calendar actualCalendar;

    private static Payroll pay_default = new Payroll();

    private Payroll() {
        this.employees = new ArrayList<>();
        this.methodsPayments = new ArrayList<>();
        this.typesPayments = new ArrayList<>();
        this.actualCalendar = null;
    }

    public static Payroll getDefault() {
        return pay_default;
    }

    public Employee searchEmployee(int id) {
        return employees.get(id);
    }

    public int searchEmployee(String name) {
        Iterator<Employee> iterable = employees.iterator();

        int i = 0;
        while(iterable.hasNext()) {
            if(iterable.next().getName().equals(name)) break;
            i++;
        }

        if(!iterable.hasNext()) return -1;
        else return i;
    }

    public void configurations(Calendar day) {
        this.actualCalendar = day;
    }

    public int nextId() {
        return employees.size() + 1;
    }

    public Employee addEmployee(ArrayList<ArrayList<Object>> paramater) throws InvalidAttributesException, CloneNotSupportedException {
        if(paramater.size() > 5) throw new Error();

        Syndicate synd = UtilsPayroll.crateSindicate(paramater.get(1));
        IMethodsPayments meth = UtilsPayroll.createMethods(paramater.get(2));
        PaymentBills type = UtilsPayroll.createTypePayment(paramater.get(3));
        PointCalendar point = UtilsPayroll.createPoint(paramater.get(4));

        Employee item = UtilsPayroll.createEmployee(paramater.get(0), synd, meth, type, point);

        employees.add(item);

        return item;
    }

    public Employee removeEmployee(int id) {
        if(id > employees.size() || id < 0)
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
        if(pay == null) {
           return false;
        }

        Payroll.pay_default = pay;

        return true;
    }

    public boolean redo() {
        Payroll pay = backup.redo();
        if(pay == null) {
            return false;
        }

        Payroll.pay_default = pay;
        return true;
    }

    public void processSaleResult(int id, String name, double value) throws Exception {
        Employee item = employees.get(id);

        if(item == null) throw new Exception("Employee can't find");
        else if(item instanceof Commisioned) {
            ((Commisioned) item).getSales().addProduct(name, value);
        }
        else {
            throw new Exception("The eploye must a be a Comissioned");
        }
    }

    public String toString() {
        StringBuilder str =  new StringBuilder();
        for(int i = 0; i < employees.size(); i++) {
            if(employees.get(i) != null)
                str.append(i).append(": ").append(employees.get(i)).append("\n");
        }

        return str.toString();
    }

    public void processServiceChange(boolean type, int id, String name_product, double value) {
        Employee emp = employees.get(id);

        if(type) {
            emp.getDebts().addDebt(name_product, value);
        }
        else {
            emp.getDebts().removeDebt(name_product);
        }
    }

    public void runPayrollToday() {
        System.out.println(getActualCalendar().toString());

        for(int i = 0; i < 1440; i++) {
            for(Employee employee : employees) {
                if (employee.getPersonalIPayment().checkItsDay(actualCalendar)) {
                    employee.attMoney();
                    System.out.println(employee.getMethodPayment().doPayment());
                }
            }
        }
    }

    public void createEmployeePaymentSchedule() {
        ArrayList<Object> param = new ArrayList<>();
        CreateElements.typeProcess(param);
        try {
            UtilsPayroll.createTypePayment(param);
        } catch (CloneNotSupportedException | InvalidAttributesException e) {
            e.printStackTrace();
        }
    }

    public void backup(boolean type) {
        backup.backup(this, type);
    }

    public Calendar getActualCalendar() {
        return actualCalendar;
    }

    @SuppressWarnings("unchecked")
    public Payroll clone() throws CloneNotSupportedException {
        Payroll p = (Payroll) super.clone();
        p.employees = (ArrayList<Employee>) employees.clone();
        p.methodsPayments = (ArrayList<IMethodsPayments>) methodsPayments.clone();
        p.typesPayments = (ArrayList<PaymentBills>) typesPayments.clone();
        p.actualCalendar = actualCalendar.clone();
        return p;
    }

    public void changeEmployee(int id, Employee change) {
        change.setId(id);
        employees.set(id, change);
    }

    public void setEmployeeSchedule(int id) throws CloneNotSupportedException {
        int i =0;
        for (PaymentBills aux: SystemSettings.DEFAULT_TYPESPAYMENTS) {
            System.out.println(i + ": " + aux.toString());
            i++;
        }

        int aux = UtilsMain.readEntries(0, i-1);
        System.out.println("\n");

        PaymentBills type = SystemSettings.DEFAULT_TYPESPAYMENTS.get(aux).clone();
        searchEmployee(id).setPersonalIPayment(type);
    }
}