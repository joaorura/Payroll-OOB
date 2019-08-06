package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.EmployeeController;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.ExecuteEmp;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class AddEmployee implements ExecuteEmp {

    @Override
    public void execute(EmployeeController payroll) {

        System.out.println("Add employee!\n");

        ArrayList<ArrayList<Object>> param = null;

        try {
            param = UtilsMain.getDatas(payroll, null);
        } catch (InvalidAttributesException e) {
            e.printStackTrace();
        }


        try {
            System.out.println(payroll.addEmployee(param).toString());
        } catch (InvalidAttributesException | CloneNotSupportedException e) {
            System.out.println("Impossível adicionar funcionário.");
        }
    }
}
