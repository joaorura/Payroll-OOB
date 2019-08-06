package interfaces.system.controlers;

import funcionabilities.Commisioned;
import funcionabilities.Employee;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.calendar.Calendar;
import interfaces.SystemSettings;
import interfaces.system.Payroll;
import interfaces.system.utils.UtilsPayroll;
import interfaces.user.UtilsMain;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.Iterator;

public class EmployeeController {
    ArrayList<Employee> employees;

    public EmployeeController(Payroll pay) {
        this.employees = pay.employees;
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

    public Employee addEmployee(ArrayList<ArrayList<Object>> paramater) throws InvalidAttributesException, CloneNotSupportedException {
        Employee item = UtilsPayroll.processEmployee(paramater);

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
            throw new Error("The eploye must a be a Comissioned");
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

    public void changeEmployee(int id, ArrayList<ArrayList<Object>> change) throws CloneNotSupportedException, InvalidAttributesException {
        Employee item = UtilsPayroll.processEmployee(change);
        employees.set(id, item);
    }

    public void setEmployeeSchedule(int id) throws CloneNotSupportedException {
        int i = 0;
        for (PaymentBills aux : SystemSettings.DEFAULT_TYPE_PAYMENTS) {
            System.out.println(i + ": " + aux.toString());
            i++;
        }

        int aux = UtilsMain.readEntries(0, i - 1);
        System.out.println("\n");

        PaymentBills type = SystemSettings.DEFAULT_TYPE_PAYMENTS.get(aux).clone();
        searchEmployee(id).setPersonalIPayment(type);
    }
}
