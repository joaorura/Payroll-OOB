package interfaces.user.funcionabilities.problematics;

import interfaces.system.Payroll;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.Execute;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class AddEmployee implements Execute {

    @Override
    public void execute(Payroll payroll) {

        System.out.println("Add employee!\n");

        ArrayList<ArrayList<Object>> param = null;

        try {
            param = UtilsMain.getDatas(null);
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
