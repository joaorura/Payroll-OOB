import interfaces.system.Payroll;
import interfaces.system.controlers.EmployeeController;
import interfaces.system.controlers.SystemController;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.Restore;

import static interfaces.user.UtilsMain.readEntries;
import static interfaces.user.problematics.Process.processEntries;

public class Main {
    public static void main(String[] args) {
        System.out.println("Booting the system!\n" +
                "\tInitializing settings!\n");
        Payroll payroll = new Payroll(UtilsMain.getDate());
        SystemController sysControll = new SystemController(payroll);
        EmployeeController empControll = new EmployeeController(payroll);

        int input;
        while (true) {
            UtilsMain.printIntro();
            input = readEntries(0, 10);

            switch (input) {
                case 9:
                    payroll = Restore.execute(sysControll);
                    sysControll = new SystemController(payroll);
                    empControll = new EmployeeController(payroll);

                case 10:
                    return;

                default:
                    if (!processEntries(payroll, sysControll, empControll, input)) {
                        System.out.println("Your operation failed, the system does not complete its action. Please" +
                                "do it again!");
                        return;
                    }
            }
        }
    }
}
