package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.EmployeeController;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.ExecuteEmp;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class ChangeEmployeeDetails implements ExecuteEmp {
    @Override
    public void execute(EmployeeController payroll) {
        int id = UtilsMain.identification(payroll);

        System.out.println("ExecuteEmp changes of employee");

        try {
            ArrayList<ArrayList<Object>> param = UtilsMain.getDatas(payroll, payroll.searchEmployee(id));
            payroll.changeEmployee(id, param);

        } catch (CloneNotSupportedException | InvalidAttributesException e) {
            System.out.println("Error in add employee.");
        }
    }
}
