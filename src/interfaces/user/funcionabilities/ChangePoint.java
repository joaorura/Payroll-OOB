package interfaces.user.funcionabilities;

import funcionabilities.functional_aids.calendar.Calendar;
import interfaces.system.controlers.EmployeeController;
import interfaces.user.UtilsMain;

public class ChangePoint implements ExecuteEmp {

    @Override
    public void execute(EmployeeController payroll) {
        System.out.println("\nProcess Point Card!\n");

        System.out.print("Start of turn: ");
        Calendar start = UtilsMain.getDate();

        System.out.println("End of turn: ");
        Calendar end = UtilsMain.getDate();

        int id = UtilsMain.identification(payroll);
        payroll.processPointCard(id, start, end);
    }
}
