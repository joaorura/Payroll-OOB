package interfaces.user.funcionabilities.problematics;

import interfaces.system.Payroll;
import interfaces.system.controlers.EmployeeController;
import interfaces.system.controlers.SystemController;
import interfaces.user.funcionabilities.Execute;
import interfaces.user.utils.UtilsSystem;
import interfaces.user.problematics.UtilsProblematicCreate;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

import static interfaces.user.utils.UtilsSystem.readEntries;

public class ChangePayment implements Execute {
    private static final String error = "Error in create employee scheduler or in set schedule.";

    @Override
    public void execute() throws Error {
        EmployeeController empControl = EmployeeController.getMainEmpControl();
        SystemController sysControl = SystemController.getSysControl();
        Payroll payroll = Payroll.getMainPayroll();

        System.out.println("Create Employee Payment Schedule / Set Employee Payment Schedule\n" +
                "\t0: Create a employee schedule\n" +
                "\t1: Set a employee schedule\n");

        if (UtilsSystem.readEntries(0, 1) == 0) {
            ArrayList<Object> param = new ArrayList<>();
            try {
                UtilsProblematicCreate.typeProcess(payroll,true, param);
            } catch (InvalidAttributesException e) {
                throw new Error(error);
            }

            sysControl.createEmployeePaymentSchedule(payroll, param);
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
