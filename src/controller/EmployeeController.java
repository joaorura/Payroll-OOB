package controller;

import model.problematics.Commissioned;
import model.problematics.Employee;
import model.problematics.PaymentBills;
import model.problematics.Calendar;
import view.utils.UtilsSystem;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.Iterator;

public class EmployeeController {
    private static final EmployeeController mainEmpControl = new EmployeeController(Payroll.getMainPayroll());

    private Payroll payroll;
    private ArrayList<Employee> employees;

    private EmployeeController(Payroll pay) {
        this.payroll = pay;
        this.employees = pay.getEmployees();
    }

    public  void reconfigure() {
        Payroll pay = Payroll.getMainPayroll();
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

    public Employee addEmployee(ArrayList<ArrayList<Object>> parameters) throws Error {
        Employee employee;

        try {
            employee = UtilsPayroll.processEmployee(parameters);
        } catch (InvalidAttributesException | CloneNotSupportedException e) {
            throw new Error("Error in add new employee");
        }

        if (employee.getId() == employees.size()) {
            employees.add(employee);
        } else {
            employees.set(employee.getId(), employee);
        }


        return employee;
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
        else if (item instanceof Commissioned) {
            ((Commissioned) item).getSales().addProduct(name, value);
        } else {
            throw new Error("The employee must a be a Commissioned");
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
            item = UtilsPayroll.processEmployee(change);
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
    
    public static EmployeeController getMainEmpControl() {
        return mainEmpControl;
    }
}
