package interfaces.user.funcionabilities;

import interfaces.system.controlers.EmployeeController;

public class PrintState implements ExecuteEmp {
    @Override
    public void execute(EmployeeController payroll) {
            System.out.println("State: \n\t" + payroll.toString());
    }
}
