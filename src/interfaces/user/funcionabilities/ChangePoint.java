package interfaces.user.funcionabilities;

import funcionabilities.functional_aids.calendar.Calendar;
import interfaces.system.Payroll;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.Execute;

public class ChangePoint implements Execute {

    @Override
    public void execute(Payroll payroll) {
        System.out.println("\nProcess Point Card!\n");

        System.out.print("Start of turn: ");
        Calendar start = UtilsMain.getDate();

        System.out.println("End of turn: ");
        Calendar end = UtilsMain.getDate();

        int id = UtilsMain.identification();
        payroll.processPointCard(id, start, end);
    }
}
