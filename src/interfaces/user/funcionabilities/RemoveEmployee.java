package interfaces.user.funcionabilities;

import funcionabilities.Employee;
import interfaces.system.controlers.EmployeeController;
import interfaces.user.utils.UtilsEmployee;

public class RemoveEmployee implements ExecuteEmp {
    @Override
    public void execute(EmployeeController payroll) {
        System.out.println("\nRemove Employee!\n");
        Employee empAux = payroll.removeEmployee(UtilsEmployee.identifier(payroll));

        if (empAux == null) {
            System.out.println("Error in remove employee.");
        } else {
            System.out.println("Removed: \n\t" + empAux.toString());
        }
    }
}
