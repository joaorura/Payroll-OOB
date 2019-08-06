package interfaces.user.funcionabilities.problematics;

import interfaces.system.Payroll;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.Execute;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class ChangeEmployeeDetails implements Execute {
    @Override
    public void execute(Payroll payroll) {
        int id = UtilsMain.identification();

        System.out.println("Execute changes of employee");

        try {
            ArrayList<ArrayList<Object>> param = UtilsMain.getDatas(payroll.searchEmployee(id));
            payroll.changeEmployee(id, param);

        } catch (CloneNotSupportedException | InvalidAttributesException e) {
            System.out.println("Error in add employee.");
        }
    }
}
