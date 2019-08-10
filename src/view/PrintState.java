package view;

import controller.Payroll;

public class PrintState implements Execute {
    @Override
    public void execute() {
        System.out.println("State: \n\t" + Payroll.getMainPayroll().getInformations());
    }
}
