package interfaces.system;

import funcionabilities.Employee;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.calendar.Calendar;
import funcionabilities.functional_aids.transactions.BankAccount;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class Payroll implements Cloneable{
    private Restore backup = new Restore();
    private Calendar actualCalendar;
    private BankAccount accountCompany;

    private ArrayList<Employee> employees = new ArrayList<>();
    private  ArrayList<PaymentBills> defaultTypePayments = new ArrayList<>();

    public Payroll(BankAccount accountCompany, Calendar calendar) throws RuntimeException {
        this.accountCompany = accountCompany;
        this.actualCalendar = calendar;
        initializeDefaults();
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
        ArrayList<PaymentBills> aux = new ArrayList<>();
        for (PaymentBills a: defaultTypePayments) {
            aux.add(a);
        }

        return aux;
    }

    public BankAccount getAccountCompany() {
        return accountCompany;
    }
}
