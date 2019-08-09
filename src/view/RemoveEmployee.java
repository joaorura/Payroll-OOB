package view;

import model.problematics.Employee;
import controller.EmployeeController;
import view.utils.UtilsEmployee;

public class RemoveEmployee implements Execute {
    @Override
    public void execute() {
        EmployeeController empControl = EmployeeController.getMainEmpControl();

        System.out.println("\nRemove Employee!\n");
        Employee empAux = empControl.removeEmployee(UtilsEmployee.identifier());

        if (empAux == null) {
            System.out.println("Error in remove employee.");
        } else {
            System.out.println("Removed: \n\t" + empAux.toString());
        }
    }
}
