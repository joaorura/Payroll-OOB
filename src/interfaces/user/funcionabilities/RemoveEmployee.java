package interfaces.user.funcionabilities;

import funcionabilities.Employee;
import interfaces.system.Payroll;
import interfaces.user.UtilsMain;

public class RemoveEmployee implements Execute{
    @Override
    public void execute(Payroll payroll) {
        System.out.println("\nRemove Employee!\n");
        Employee empAux = payroll.removeEmployee(UtilsMain.identification());

        if (empAux == null) {
            System.out.println("Error in remove employee.");
        } else {
            System.out.println("Removed: \n\t" + empAux.toString());
        }
    }
}
