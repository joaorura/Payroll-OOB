package interfaces.system;

import model.problematics.Employee;
import model.problematics.PaymentBills;
import model.problematics.Calendar;
import model.BankAccount;
import interfaces.system.memento.Restore;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class Payroll implements Cloneable{
    private static Payroll mainPayroll;

    static {
        try {
            mainPayroll = new Payroll();
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error attempting to boot the system, please contact the developer.");
        }
    }

    private final Restore backup = new Restore();
    private Calendar actualCalendar;
    private BankAccount accountCompany;

    private ArrayList<Employee> employees = new ArrayList<>();
    private final ArrayList<PaymentBills> defaultTypePayments = new ArrayList<>();

    private Payroll() throws InstantiationException {
        try {
            this.accountCompany = null;
            this.actualCalendar = null;
            initializeDefaults();
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
            throw new InstantiationException("Error instantiating payroll.");
        }
    }

    private void initializeDefaults() {
        try {
            defaultTypePayments.add(new PaymentBills(6, 0, 0));
            defaultTypePayments.add(new PaymentBills(-1, 0, 0));
            defaultTypePayments.add(new PaymentBills(6, 1, 0));
        } catch (CloneNotSupportedException | InvalidAttributesException e) {
            throw new RuntimeException("Error in create elements defaults of default type payments.");
        }
    }

    public void reconfigure(BankAccount accountCompany, Calendar calendar) throws RuntimeException {
            this.accountCompany = accountCompany;
            this.actualCalendar = calendar;
    }

    public void backup(boolean type) {
        backup.backup(this, type);
    }

    public void addEmployeeSchedule(PaymentBills pb) {
        defaultTypePayments.add(pb);
    }

    @Override
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) != null)
                str.append(i).append(": ").append(employees.get(i).toString()).append("\n");
        }

        return str.toString();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public Restore getBackup() {
        return backup;
    }

    public Calendar getActualCalendar() {
        return actualCalendar;
    }

    public ArrayList<PaymentBills> getDefaultTypePayments() {
        return new ArrayList<>(defaultTypePayments);
    }

    public BankAccount getAccountCompany() {
        return accountCompany;
    }

    public static Payroll getMainPayroll() {
        return mainPayroll;
    }

    public static void setMainPayroll(Payroll mainPayroll) {
        Payroll.mainPayroll = mainPayroll;
    }
}
