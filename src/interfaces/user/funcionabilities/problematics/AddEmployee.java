package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.EmployeeController;
import interfaces.user.utils.UtilsEmployee;
import interfaces.user.funcionabilities.ExecuteEmp;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class AddEmployee implements ExecuteEmp {
    private static final String error = "Unable to add employee.";
    @Override
    public void execute(EmployeeController empControl) {

        System.out.println("Add employee!\n");
        ArrayList<ArrayList<Object>> param = null;

        try {
            param = UtilsEmployee.getDatas(empControl, null);
        } catch (InvalidAttributesException e) {
            System.out.println(error);
        }

        try {
            System.out.println(empControl.addEmployee(empControl.getPayroll(), param).toString());
        } catch (Error e) {
            System.out.println(e.getMessage() + "\n" + error);
        }
    }
}
