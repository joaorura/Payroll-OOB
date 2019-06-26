import funcionabilities.Commisioned;
import funcionabilities.Employee;
import funcionabilities.Hourly;
import funcionabilities.Salaried;
import funcionabilities.auxiliary_entities.ISyndicates;
import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.calendar.IPointCalendar;
import funcionabilities.functional_aids.calendar.PointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.payments.PaymentBills;
import funcionabilities.functional_aids.sales.ISalesList;
import funcionabilities.functional_aids.transactions.*;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.*;

public class Payroll implements Cloneable{
    private ArrayList<Employee> employees;
    private IMemento<Payroll> backup;
    private ArrayList<IMethodsPayments> methodsPayments;
    private ArrayList<ITypePayments> typesPayments;
    private GregorianCalendar actualCalendar;

    private static Payroll pay_default = new Payroll();

    private Payroll() {
        this.employees = new ArrayList<>();
        this.backup = new Restore();
        this.methodsPayments = new ArrayList<>();
        this.typesPayments = new ArrayList<>();
        this.actualCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
    }

    private int search(String name) {
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

    public void configurations(GregorianCalendar day) {
        this.actualCalendar = day;
    }

    public int nextId() {
        return employees.size() + 1;
    }

    Employee searchEmployee(int id) {
        return employees.get(id);
    }

    Employee searchEmployee(String name) {
        return searchEmployee(search(name));
    }


    Employee addEmployee(ArrayList<Object>[] paramater) {
        boolean aux = false;
        int id = employees.size() + 1;

        ISyndicates synd = null;
        if (SystemSettings.TYPE_SYNDICATES.get(paramater[1].get(0))[0] == 0) {
            synd = new Syndicate((String) paramater[1].get(1), (double) paramater[1].get(2));
        }

        IMethodsPayments meth = null;
        switch (SystemSettings.TYPE_METHODS_PAYMENTS.get(paramater[2].get(0))[0]) {
            case 0:
                meth = new Deposit((BankAcount) paramater[2].get(1), (Double) paramater[2].get(2), (String) paramater[2].get(3));
                break;

            case 1:
                meth = new CheckHands((BankAcount) paramater[2].get(1), (Double) paramater[2].get(2), (String) paramater[2].get(3),
                        (int) paramater[2].get(4));
                break;

            case 2:
                meth = new CheckPostOffices((BankAcount) paramater[2].get(1),(Double) paramater[2].get(2), (String) paramater[2].get(3),
                        (String) paramater[2].get(4));
                break;

        }

        ITypePayments type = null;
        switch (SystemSettings.TYPE_PAYMENTS.get(paramater[3].get(0))[0]) {
            case -1:
                type = (ITypePayments) paramater[3].get(1);
                break;

            case 0: type = new PaymentBills((GregorianCalendar) paramater[3].get(1), (int) paramater[3].get(2),
                    (int) paramater[3].get(3), (int) paramater[3].get(4));
                break;
        }

        IPointCalendar point = null;
        if (SystemSettings.TYPE_POINTS.get(paramater[4].get(0))[0] == 0) {
            point = new PointCalendar((int) paramater[4].get(1));
        }

        if(meth == null || type == null || point == null) {
            throw new Error();
        }

        Employee item = null;
        switch (SystemSettings.TYPE_EMPLOYEES.get(paramater[0].get(0))[0]) {
            case 0:
                item = new Hourly((String) paramater[0].get(1), (String) paramater[0].get(2), (int) paramater[0].get(3),
                        synd, meth, type, point, (int) paramater[0].get(4), (Double) paramater[0].get(5),
                        (Double) paramater[0].get(6));
                break;

            case 1:
                item = new Salaried((String) paramater[0].get(1), (String) paramater[0].get(2), (int) paramater[0].get(3),
                        synd, meth, type, point, (Double) paramater[0].get(4));

                break;

            case 2:
                item = new Commisioned((String) paramater[0].get(1), (String) paramater[0].get(2), (int) paramater[0].get(3),
                        synd, meth, type, point, (Double) paramater[0].get(4), (Double) paramater[0].get(5));
                break;

            default:
                throw new IllegalArgumentException();
        }

        employees.add(item);

        return item;
    }

    Employee removeEmployee(int id) {
        if(id > employees.size() || id < 0)
            return null;
        else
            return employees.remove(id);
    }

    Employee removeEmployee(String name) {
        return removeEmployee(search(name));
    }

    boolean processPointCard(int id, GregorianCalendar start, GregorianCalendar end) {
        Employee employee = employees.get(id);
        if(employee == null) return false;

        employee.getWorker().markPoint(start, end);
        return true;
    }

    boolean processPointCard(String name, GregorianCalendar start, GregorianCalendar end) {
        return processPointCard(search(name), start, end);
    }

    boolean undo() {
        Payroll pay = backup.undo();
        if(pay == null) {
           return false;
        }

        Payroll.pay_default = pay;
        return true;
    }

    boolean redo() {
        Payroll pay = backup.redo();
        if(pay == null) {
            return false;
        }

        Payroll.pay_default = pay;
        return true;
    }

    boolean processSaleResult(int id, String name, double value) throws Exception {
        Employee item = employees.get(id);

        if(item == null) {
            throw new Exception("Employee can't find");
        }
        if(item instanceof Commisioned) {
            ((Commisioned) item).getSales().addProduct(name, value);
            return true;
        }
        else {
            throw new Exception("The eploye must a be a Comissioned");
        }
    }

    boolean processSaleResult(String func_name, String name, double value) throws Exception {
        return processSaleResult(search(func_name), name, value);
    }

    boolean processServiceChange(boolean type, int id, String name_product, double value) {
        Employee emp = employees.get(id);

        if(type) {
            emp.addDebit(name_product, value);
        }
        else {
            emp.removeDebit(name_product, value);
        }

        return false;
    }

    boolean processServiceChange(boolean type, String name, String name_product, double value) {
        return processServiceChange(type, search(name), name_product, value);
    }

    boolean processEmployeeDetail() {
        return false;
    }

    boolean runPayrollToday() {
        return false;
    }

    boolean createEmployerPaymentSchedule() {
        return false;
    }

    void setEmployerPaymentSchedule(ITypePayments tp) {
    }

    boolean backup(boolean type) {
        return backup.backup(this, true);
    }

    public GregorianCalendar getActualCalendar() {
        return actualCalendar;
    }

    static Payroll getDefault() {
        return pay_default;
    }


    public Payroll clone() throws CloneNotSupportedException {
        return (Payroll) super.clone();
    }
}