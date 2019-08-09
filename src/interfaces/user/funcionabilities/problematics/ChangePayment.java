package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.EmployeeController;
import interfaces.system.controlers.SystemController;
import interfaces.user.utils.UtilsSystem;
import interfaces.user.problematics.UtilsProblematicCreate;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

import static interfaces.user.utils.UtilsSystem.readEntries;

public class ChangePayment {
    private static final String error = "Error in create employee scheduler or in set schedule.";

    public static void execute(EmployeeController empControl, SystemController sysControl) throws Error {
        System.out.println("Create Employee Payment Schedule / Set Employee Payment Schedule\n" +
                "\t0: Create a employee schedule\n" +
                "\t1: Set a employee schedule\n");

        if (UtilsSystem.readEntries(0, 1) == 0) {
            ArrayList<Object> param = new ArrayList<>();
            try {
                UtilsProblematicCreate.typeProcess(empControl.getPayroll(),true, param);
            } catch (InvalidAttributesException e) {
                throw new Error(error);
            }

            sysControl.createEmployeePaymentSchedule(empControl.getPayroll(), param);
        }
        else {
            int id;
            UtilsSystem.printIdentification();

                if (readEntries(0, 1) == 0) {
                    id = (int) UtilsSystem.readEntries(Integer.class);
                } else {
                    String name = UtilsSystem.takeString();
                    id = empControl.searchEmployee(name);
                }

                if (empControl.searchEmployee(id) == null) {
                    throw new Error(error);
                }

                empControl.setEmployeeSchedule(id);
        }
    }
}
