package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.EmployeeController;
import interfaces.user.utils.UtilsEmployee;
import interfaces.user.funcionabilities.ExecuteEmp;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class AddEmployee implements ExecuteEmp {
    private static final String error = "Impossível adicionar funcionário.";
    @Override
    public void execute(EmployeeController empControll) {

        System.out.println("Add employee!\n");
        ArrayList<ArrayList<Object>> param = null;

        try {
            param = UtilsEmployee.getDatas(empControll, null);
        } catch (InvalidAttributesException e) {
            System.out.println(error);
        }

        try {
            System.out.println(empControll.addEmployee(empControll.getPayroll(), param).toString());
        } catch (Error e) {
            System.out.println(e.getMessage() + "\n" + error);
        }
    }
}
