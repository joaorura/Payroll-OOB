package interfaces.system;

import funcionabilities.Employee;
import funcionabilities.functional_aids.calendar.Calendar;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class Payroll implements Cloneable{
    private Restore backup = new Restore();
    private ArrayList<Employee> employees = new ArrayList<>();
    private Calendar actualCalendar;

    public Payroll(Calendar calendar) {
        actualCalendar = calendar;
    }

    public void backup(boolean type) {
        backup.backup(this, type);
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
}
