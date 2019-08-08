package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.SystemController;
import interfaces.user.utils.UtilsSystem;

public class RunPay {
    public static void execute(SystemController payroll) {
        System.out.println("Enter the amount days will be processed: \n");
        int amount = UtilsSystem.readEntries(0, Integer.MAX_VALUE);

        try {
            for (int i = 0; i < amount; i++) payroll.runPayrollToday();
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }
}
