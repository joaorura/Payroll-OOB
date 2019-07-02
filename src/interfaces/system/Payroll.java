package interfaces.system;

import funcionabilities.*;
import funcionabilities.auxiliary_entities.ISyndicates;
import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.calendar.IPointCalendar;
import funcionabilities.functional_aids.calendar.PointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.payments.PaymentBills;
import funcionabilities.functional_aids.transactions.*;
import interfaces.SystemSettings;

import java.util.*;

public class Payroll implements Cloneable{
    private ArrayList<Employee> employees;
    private static final IMemento<Payroll> backup = new Restore();
    private ArrayList<IMethodsPayments> methodsPayments;
    private ArrayList<ITypePayments> typesPayments;
    private GregorianCalendar actualCalendar;

    private static Payroll pay_default = new Payroll();

    private Payroll() {
        this.employees = new ArrayList<>();
        this.methodsPayments = new ArrayList<>();
        this.typesPayments = new ArrayList<>();
        this.actualCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
    }

    public static Payroll getDefault() {
        return pay_default;
    }

    public int searchEmployee(String name) {
        Iterator<Employee> iterable = employees.iterator();

        int i = 0;
        Employee aux;
        while(iterable.hasNext()) {
            if(iterable.next().getName().equals(name)) break;
            i++;
        }

        if(!iterable.hasNext()) return -1;
        else return i;
    }

    public Employee searchEmployee(int id) {
        return employees.get(id);
    }

    public void configurations(GregorianCalendar day) {
        this.actualCalendar = day;
    }

    public int nextId() {
        return employees.size() + 1;
    }

    public ITypePayments createTypePayment(ArrayList<Object> paramater) {
        ITypePayments type = null;
        switch (SystemSettings.TYPE_PAYMENTS.get(paramater.get(0))[0]) {
            case -1:
                type = (ITypePayments) paramater.get(1);
                break;

            case 0: type = new PaymentBills((GregorianCalendar) paramater.get(1), (int) paramater.get(2),
                    (int) paramater.get(3), (int) paramater.get(4));
                break;
        }

        return type;
    }

    public Employee addEmployee(ArrayList<ArrayList<Object>> paramater) {
        boolean aux = false;
        int id = employees.size() + 1;

        ISyndicates synd = null;
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

        ITypePayments type = createTypePayment(paramater.get(3));

        IPointCalendar point = null;
        if (SystemSettings.TYPE_POINTS.get(paramater.get(4).get(0))[0] == 0) {
            point = new PointCalendar((int) paramater.get(4).get(1));
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

    public void processPointCard(int id, GregorianCalendar start, GregorianCalendar end) {
        Employee emp = searchEmployee(id);
        emp.getWorker().markPoint(start, end);
    }

    public void processPointCard(String name, GregorianCalendar start, GregorianCalendar end) {
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

    boolean createEmployeePaymentSchedule() {
        return false;
    }

    public void backup(boolean type) {
        backup.backup(this, true);
    }

    public GregorianCalendar getActualCalendar() {
        return actualCalendar;
    }

    public Payroll clone() throws CloneNotSupportedException {
        Payroll p = (Payroll) super.clone();
        p.employees = (ArrayList<Employee>) employees.clone();
        p.methodsPayments = (ArrayList<IMethodsPayments>) methodsPayments.clone();
        p.typesPayments = (ArrayList<ITypePayments>) typesPayments.clone();
        p.actualCalendar = (GregorianCalendar) actualCalendar.clone();
        return p;
    }

    public void changeEmployee(int id, Employee change) {
        employees.set(id, change);
    }

    public void changeEmployee(String name, Employee change) {
        changeEmployee(searchEmployee(name), change);
    }
}