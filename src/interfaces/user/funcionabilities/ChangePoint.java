package interfaces.user.funcionabilities;

import funcionabilities.functional_aids.calendar.Calendar;
import interfaces.system.controlers.EmployeeController;
import interfaces.user.utils.UtilsEmployee;

import static interfaces.user.utils.UtilsCalendar.getDate;

public class ChangePoint implements ExecuteEmp {

    @Override
    public void execute(EmployeeController payroll) {
        System.out.println("\nProcess Point Card!\n");

        System.out.print("Start of turn: ");
        Calendar start = getDate();

        System.out.println("End of turn: ");
        Calendar end = getDate();

        int id = UtilsEmployee.identifier(payroll);
        payroll.processPointCard(id, start, end);
    }
}
