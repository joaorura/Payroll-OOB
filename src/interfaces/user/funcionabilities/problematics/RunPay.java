package interfaces.user.funcionabilities.problematics;

import interfaces.system.Payroll;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.Execute;

import javax.naming.directory.InvalidAttributesException;

public class RunPay implements Execute {
    @Override
    public void execute(Payroll payroll) {
        System.out.println("Enter the amount days will be processed: \n");
        int amount = UtilsMain.readEntries(0, Integer.MAX_VALUE);

        try {
            for (int i = 0; i < amount; i++) payroll.runPayrollToday();
        } catch (InvalidAttributesException e) {
            e.printStackTrace();
        }
    }
}
