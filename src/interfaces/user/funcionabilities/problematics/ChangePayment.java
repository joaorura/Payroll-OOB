package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.EmployeeController;
import interfaces.system.controlers.SystemController;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.ExecuteEmp;

import static interfaces.user.UtilsMain.readEntries;

public class ChangePayment {
    public static void execute(EmployeeController empControll, SystemController sysControll) {
        System.out.println("Create Employee Payment Schedule / Set Employee Payment Schedule\n" +
                "\t0: Create a employee schedule\n" +
                "\t1: Set a employee schedule\n");

        if (UtilsMain.readEntries(0, 1) == 0) {
            sysControll.createEmployeePaymentSchedule();
        }
        else {
            int id;
            try {
                UtilsMain.printIdentification();

                if (readEntries(0, 1) == 0) {
                    id = (int) UtilsMain.readEntries(Integer.class);
                } else {
                    String name = UtilsMain.takeString();
                    id = empControll.searchEmployee(name);
                }

                if (empControll.searchEmployee(id) == null) {
                    throw new Error();
                }

                empControll.setEmployeeSchedule(id);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
}
