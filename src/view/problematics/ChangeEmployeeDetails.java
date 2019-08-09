package view.problematics;

import controller.EmployeeController;
import view.utils.UtilsEmployee;
import view.Execute;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class ChangeEmployeeDetails implements Execute {
    private static final String ERROR = "Error in add employee.";

    @Override
    public void execute() {
        EmployeeController empControl = EmployeeController.getMainEmpControl();

        int id = UtilsEmployee.identifier();

        System.out.println("Execute changes of employee");

        try {
            ArrayList<ArrayList<Object>> param = UtilsEmployee.getDatas(empControl.searchEmployee(id));
            empControl.changeEmployee(id, param);

        } catch (Error e) {
            System.out.println(e.getMessage() + "\n" + ERROR);
        } catch (InvalidAttributesException e) {
            System.out.println(ERROR);
        }
    }
}
