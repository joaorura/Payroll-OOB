package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.SystemController;
import interfaces.user.funcionabilities.Execute;
import interfaces.user.utils.UtilsSystem;

public class RunPay implements Execute {
    public void execute() {
        System.out.println("Enter the amount days will be processed: \n");
        int amount = UtilsSystem.readEntries(0, Integer.MAX_VALUE);

        try {
            for (int i = 0; i < amount; i++) SystemController.getSysControl().runPayrollToday();
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }
}
