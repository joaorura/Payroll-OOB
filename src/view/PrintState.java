package view;

import controller.EmployeeController;

public class PrintState implements Execute {
    @Override
    public void execute() {
        System.out.println("State: \n\t" + EmployeeController.getMainEmpControl().toString());
    }
}
