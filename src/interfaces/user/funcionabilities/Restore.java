package interfaces.user.funcionabilities;

import interfaces.system.Payroll;

import static interfaces.user.UtilsMain.readEntries;

public class Restore implements Execute {
    @Override
    public void execute(Payroll payroll) {
        System.out.println("Você deseja:");
        System.out.println("\t0: Desfazer");
        System.out.println("\t1: Refazer");

        if (readEntries(0, 1) == 0) {
            payroll.undo();
        }
        else {
            payroll.redo();
        }
    }
}
