import interfaces.system.Payroll;
import interfaces.system.controlers.EmployeeController;
import interfaces.system.controlers.SystemController;
import interfaces.user.funcionabilities.Restore;

import static interfaces.user.utils.UtilsCalendar.getDate;
import static interfaces.user.problematics.Process.processEntries;
import static interfaces.user.utils.UtilsSystem.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Booting the system!\n" +
                "\tInitializing settings!\n");
        Payroll payroll = new Payroll(getBank(), getDate());
        SystemController sysControll = new SystemController(payroll);
        EmployeeController empControll = new EmployeeController(payroll);

        int input;
        //noinspection InfiniteLoopStatement
        while (true) {
            printIntro();
            input = readEntries(0, 10);

            if(input == 9) {
                payroll = Restore.execute(sysControll);
                sysControll = new SystemController(payroll);
                empControll = new EmployeeController(payroll);
            }
            else if(input == 10) {
                System.out.println("The system is gonna be closed.");
            }
            else if(!processEntries(payroll, sysControll, empControll, input)){
                System.out.println("Your operation failed, the system does not complete its action. Please" +
                        "do it again!");
            }
        }
    }
}
