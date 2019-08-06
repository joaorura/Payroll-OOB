package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.SystemController;
import interfaces.user.UtilsMain;
import javax.naming.directory.InvalidAttributesException;

public class RunPay {
    public static void execute(SystemController payroll) {
        System.out.println("Enter the amount days will be processed: \n");
        int amount = UtilsMain.readEntries(0, Integer.MAX_VALUE);

        try {
            for (int i = 0; i < amount; i++) payroll.runPayrollToday();
        } catch (InvalidAttributesException e) {
            e.printStackTrace();
        }
    }
}
