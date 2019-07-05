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

    int searchEmployee(String name) {
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

    public Employee searchEmployee(int id) {
        return employees.get(id);
    }

    public PaymentBills createTypePayment(ArrayList<Object> paramater) throws CloneNotSupportedException, InvalidAttributesException {
        PaymentBills type = null;
        switch (SystemSettings.TYPE_PAYMENTS.get(paramater.get(0))[0]) {
            case -1:
                type = (PaymentBills) paramater.get(1);
                break;

            case 0: type = new PaymentBills((Calendar) paramater.get(1), (int) paramater.get(2),
                    (int) paramater.get(3), (int) paramater.get(4));
                break;
        }

        return type;
    }

    public Employee addEmployee(ArrayList<ArrayList<Object>> paramater) throws InvalidAttributesException, CloneNotSupportedException {
        Syndicate synd = null;
        if (SystemSettings.TYPE_SYNDICATES.get(paramater.get(1).get(0))[0] == 0) {
            synd = new Syndicate((String) paramater.get(1).get(1), (double) paramater.get(1).get(2));
        }

        IMethodsPayments meth = null;
        switch (SystemSettings.TYPE_METHODS_PAYMENTS.get(paramater.get(2).get(0))[0]) {
            case 0:
                meth = new Deposit((BankAcount) paramater.get(2).get(1), (Double) paramater.get(2).get(2),
                        (String) paramater.get(2).get(3));
                break;

            case 1:
                meth = new CheckHands((BankAcount) paramater.get(2).get(1), (Double) paramater.get(2).get(2),
                        (String) paramater.get(2).get(3), (int) paramater.get(2).get(4));
                break;

            case 2:
                meth = new CheckPostOffices((BankAcount) paramater.get(2).get(1),(Double) paramater.get(2).get(2),
                        (String) paramater.get(2).get(3), (String) paramater.get(2).get(4));
                break;

        }

        PaymentBills type = createTypePayment(paramater.get(3));

        PointCalendar point = null;
        if (SystemSettings.TYPE_POINTS.get(paramater.get(4).get(0))[0] == 0) {
            point = new PointCalendar();
        }

        if(meth == null || type == null || point == null) {
            throw new Error();
        }

        Employee item ;
        switch (SystemSettings.TYPE_EMPLOYEES.get(paramater.get(0).get(0))[0]) {
            case 0:
                item = new Hourly((String) paramater.get(0).get(1), (String) paramater.get(0).get(2), (int) paramater.get(0).get(3),
                        synd, meth, type, point, (int) paramater.get(0).get(4), (Double) paramater.get(0).get(5),
                        (Double) paramater.get(0).get(6));
                break;

            case 1:
                item = new Salaried((String) paramater.get(0).get(1), (String) paramater.get(0).get(2),
                        (int) paramater.get(0).get(3), synd, meth, type, point, (Double) paramater.get(0).get(4));

                break;

            case 2:
                item = new Commisioned((String) paramater.get(0).get(1), (String) paramater.get(0).get(2),
                        (int) paramater.get(0).get(3), synd, meth, type, point, (Double) paramater.get(0).get(4), (Double) paramater.get(0).get(5));
                break;

            default:
                throw new IllegalArgumentException();
        }

        employees.add(item);

        return item;
    }

    public Employee removeEmployee(int id) {
        if(id > employees.size() || id < 0)
            return null;
        else
            return employees.set(id, null);
    }

    public Employee removeEmployee(String name) {
        return removeEmployee(searchEmployee(name));
    }

    public void processPointCard(int id, Calendar start, Calendar end) {
        Employee emp = searchEmployee(id);
        emp.getWorker().markPoint(start, end);
    }

    public void processPointCard(String name, Calendar start, Calendar end) {
        processPointCard(searchEmployee(name), start, end);
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

    public void processSaleResult(String func_name, String name, double value) throws Exception {
        processSaleResult(searchEmployee(func_name), name, value);
    }

    public void processServiceChange(boolean type, int id, String name_product, double value) {
        Employee emp = employees.get(id);

        if(type) {
            emp.addDebit(name_product, value);
        }
        else {
            emp.removeDebit(name_product, value);
        }

    }

    public void processServiceChange(boolean type, String name, String name_product, double value) {
        processServiceChange(type, searchEmployee(name), name_product, value);
    }

    public void runPayrollToday() {
        Employee emp_aux;
        for(Employee employee : employees) {
            emp_aux = employee;
            if (emp_aux.getPersonalIPayment().checkItsDay(actualCalendar)) {
                System.out.println(emp_aux.getMethodPayment().doPayment());
            }
        }
    }

    public void createEmployeePaymentSchedule() {
        ArrayList<Object> param = new ArrayList<>();
        CreateElements.typeProcess(param);
        PaymentBills type_aux = null;
        try {
            type_aux = createTypePayment(param);
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
        Object aux =  employees.clone();
        
        p.employees = (ArrayList<Employee>) employees.clone();
        p.methodsPayments = (ArrayList<IMethodsPayments>) methodsPayments.clone();
        p.typesPayments = (ArrayList<PaymentBills>) typesPayments.clone();
        p.actualCalendar = (Calendar) actualCalendar.clone();
        return p;
    }

    public void changeEmployee(int id, Employee change) {
        change.setId(id);
        employees.set(id, change);
    }

    public void changeEmployee(String name, Employee change) {
        changeEmployee(searchEmployee(name), change);
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