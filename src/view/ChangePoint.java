package view;

import model.problematics.Calendar;
import controller.EmployeeController;
import view.utils.UtilsEmployee;

import static view.utils.UtilsCalendar.getDate;

public class ChangePoint implements Execute {

    @Override
    public void execute() {
        EmployeeController payroll = EmployeeController.getMainEmpControl();

        System.out.println("\nProcess Point Card!\n");

        System.out.print("Start of turn: ");
        Calendar start = getDate();

        System.out.println("End of turn: ");
        Calendar end = getDate();

        int id = UtilsEmployee.identifier();
        payroll.processPointCard(id, start, end);
    }
}
