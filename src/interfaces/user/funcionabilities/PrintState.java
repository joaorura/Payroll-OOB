package interfaces.user.funcionabilities;

import interfaces.system.Payroll;

public class PrintState implements Execute{
    @Override
    public void execute(Payroll payroll) {
            System.out.println("State: \n\t" + payroll.toString());
    }
}
