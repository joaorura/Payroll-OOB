import funcionabilities.Employee;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.transactions.IMethodsPayments;
import memento.IMemento;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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

    Employee searchEmployee(int id) {
        return employees.get(id);
    }

    int addEmployee() {
        return -1;
    }

    int removeEmployee() {
        return -1;
    }

    boolean processPointCard() {
        return false;
    }

    boolean undo() {
        return false;
    }

    boolean redo() {
        return false;
    }

    boolean processSaleResult() {
        return false;
    }

    boolean processServiceChange() {
        return false;
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

    boolean setEmployerPaymentSchedule() {
        return false;
    }

    boolean backup(boolean type) {
        return backup.backup(this, true);
    }

    static Payroll getDefault() {
        return pay_default;
    }

    public Payroll clone() throws CloneNotSupportedException {
        return (Payroll) super.clone();
    }
}