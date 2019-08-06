package interfaces.user.funcionabilities.problematics;

import interfaces.system.Payroll;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.Execute;

import static interfaces.user.UtilsMain.readEntries;

public class ChangePayment implements Execute {
    @Override
    public void execute(Payroll payroll) {
        System.out.println("Create Employee Payment Schedule / Set Employee Payment Schedule\n" +
                "\t0: Create a employee schedule\n" +
                "\t1: Set a employee schedule\n");

        if (UtilsMain.readEntries(0, 1) == 0) {
            payroll.createEmployeePaymentSchedule();
        }
        else {
            int id;
            try {
                UtilsMain.printIdentification();

                if (readEntries(0, 1) == 0) {
                    id = (int) UtilsMain.readEntries(Integer.class);
                } else {
                    String name = UtilsMain.takeString();
                    id = payroll.searchEmployee(name);
                }

                if (payroll.searchEmployee(id) == null) {
                    throw new Error();
                }

                payroll.setEmployeeSchedule(id);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
}
