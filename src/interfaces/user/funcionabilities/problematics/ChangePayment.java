package interfaces.user.funcionabilities.problematics;

import interfaces.system.Payroll;
import interfaces.system.controlers.EmployeeController;
import interfaces.system.controlers.SystemController;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.ExecuteEmp;
import interfaces.user.problematics.UtilsProblematicCreate;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

import static interfaces.user.UtilsMain.readEntries;

public class ChangePayment {
    private static final String error = "Error in create employee scheduler or in set schedule.";

    public static void execute(EmployeeController empControll, SystemController sysControll) throws Error {
        System.out.println("Create Employee Payment Schedule / Set Employee Payment Schedule\n" +
                "\t0: Create a employee schedule\n" +
                "\t1: Set a employee schedule\n");

        if (UtilsMain.readEntries(0, 1) == 0) {
            ArrayList<Object> param = new ArrayList<>();
            try {
                UtilsProblematicCreate.typeProcess(empControll.getPayroll(),true, param);
            } catch (InvalidAttributesException e) {
                throw new Error(error);
            }

            sysControll.createEmployeePaymentSchedule(param);
        }
        else {
            int id;
            UtilsMain.printIdentification();

                if (readEntries(0, 1) == 0) {
                    id = (int) UtilsMain.readEntries(Integer.class);
                } else {
                    String name = UtilsMain.takeString();
                    id = empControll.searchEmployee(name);
                }

                if (empControll.searchEmployee(id) == null) {
                    throw new Error(error);
                }

                empControll.setEmployeeSchedule(id);
        }
    }
}
