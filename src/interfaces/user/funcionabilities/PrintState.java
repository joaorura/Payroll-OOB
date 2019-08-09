package interfaces.user.funcionabilities;

import interfaces.system.controlers.EmployeeController;

public class PrintState implements Execute {
    @Override
    public void execute() {
        System.out.println("State: \n\t" + EmployeeController.getMainEmpControl().toString());
    }
}
