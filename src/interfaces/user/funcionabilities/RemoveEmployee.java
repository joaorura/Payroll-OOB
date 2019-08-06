package interfaces.user.funcionabilities;

import funcionabilities.Employee;
import interfaces.system.controlers.EmployeeController;
import interfaces.user.UtilsMain;

public class RemoveEmployee implements ExecuteEmp {
    @Override
    public void execute(EmployeeController payroll) {
        System.out.println("\nRemove Employee!\n");
        Employee empAux = payroll.removeEmployee(UtilsMain.identification(payroll));

        if (empAux == null) {
            System.out.println("Error in remove employee.");
        } else {
            System.out.println("Removed: \n\t" + empAux.toString());
        }
    }
}
