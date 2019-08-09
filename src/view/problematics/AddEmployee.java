package view.problematics;

import controller.Payroll;
import controller.EmployeeController;
import view.utils.UtilsEmployee;
import view.Execute;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class AddEmployee implements Execute {
    private static final String error = "Unable to add employee.";

    @Override
    public void execute() {
        EmployeeController empControl = EmployeeController.getMainEmpControl();

        System.out.println("Add employee!\n");
        ArrayList<ArrayList<Object>> param = null;

        try {
            param = UtilsEmployee.getDatas(null);
        } catch (InvalidAttributesException e) {
            System.out.println(error);
        }

        try {
            System.out.println(empControl.addEmployee(Payroll.getMainPayroll(), param).toString());
        } catch (Error e) {
            System.out.println(e.getMessage() + "\n" + error);
        }
    }
}
