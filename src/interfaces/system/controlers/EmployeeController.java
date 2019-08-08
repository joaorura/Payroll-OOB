package interfaces.system.controlers;

import funcionabilities.Commisioned;
import funcionabilities.Employee;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.calendar.Calendar;
import interfaces.system.Payroll;
import interfaces.system.utils.UtilsPayroll;
import interfaces.user.utils.UtilsSystem;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.Iterator;

public class EmployeeController {
    private Payroll payroll;
    private ArrayList<Employee> employees;

    public EmployeeController(Payroll pay) {
        this.payroll = pay;
        this.employees = pay.getEmployees();
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

    public int nextId() {
        int i = 0;
        for (Employee aux : employees) {
            if (aux == null) break;
            else i++;
        }

        return i;
    }

    public Employee addEmployee(Payroll payroll, ArrayList<ArrayList<Object>> paramater) throws Error {
        Employee item;
        try {
            item = UtilsPayroll.processEmployee(payroll, paramater);
        } catch (InvalidAttributesException | CloneNotSupportedException e) {
            throw new Error("Error in add new employee");
        }

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

    public void processSaleResult(int id, String name, double value) throws Error {
        Employee item = employees.get(id);

        if (item == null) throw new Error("Employee can't find");
        else if (item instanceof Commisioned) {
            ((Commisioned) item).getSales().addProduct(name, value);
        } else {
            throw new Error("The eployee must a be a Comissioned");
        }
    }

    public void processServiceChange(boolean type, int id, String name_product, double value) {
        Employee emp = employees.get(id);

        if (type) {
            emp.getDebts().addDebt(name_product, value);
        } else {
            emp.getDebts().removeDebt(name_product);
        }
    }

    public void changeEmployee(int id, ArrayList<ArrayList<Object>> change) throws Error{
        Employee item;
        try {
            item = UtilsPayroll.processEmployee(payroll, change);
        } catch (InvalidAttributesException | CloneNotSupportedException e) {
            throw new Error("Error in make change os employee data.");
        }

        employees.set(id, item);
    }

    public void setEmployeeSchedule(int id) throws Error {
        int i = 0;
        for (PaymentBills aux : payroll.getDefaultTypePayments()) {
            System.out.println(i + ": " + aux.toString());
            i++;
        }

        int aux = UtilsSystem.readEntries(0, i - 1);
        System.out.println("\n");

        PaymentBills type;
        try {
            type = payroll.getDefaultTypePayments().get(aux).clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("Error in change employee schedule.");
        }

        searchEmployee(id).setPersonalIPayment(type);
    }

    public Payroll getPayroll() {
        return payroll;
    }
}
