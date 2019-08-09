import controller.Payroll;
import controller.EmployeeController;
import controller.SystemController;
import view.Restore;

import static view.utils.UtilsCalendar.getDate;
import static view.problematics.Process.processEntries;
import static view.utils.UtilsSystem.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Booting the system!\n" +
                "\tInitializing settings!\n");

        try {
            Payroll.getMainPayroll().reconfigure(getBank(), getDate());
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }


        int input;
        //noinspection InfiniteLoopStatement
        while (true) {
            printIntro();
            input = readEntries(0, 10);

            if(input == 9) {
                Payroll.setMainPayroll(Restore.execute());
                EmployeeController.getMainEmpControl().reconfigure();
                SystemController.getSysControl().reconfigure();
            }
            else if(input == 10) {
                System.out.println("The system is gonna be closed.");
            }
            else if(!processEntries(input)){
                System.out.println("Your operation failed, the system does not complete its action. Please" +
                        "do it again!");
            }
        }
    }
}
