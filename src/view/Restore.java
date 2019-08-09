package view;

import controller.Payroll;
import controller.SystemController;

import static view.utils.UtilsSystem.readEntries;

public class Restore {
    public static Payroll execute() {
        SystemController sysControl = SystemController.getSysControl();
        System.out.println("Você deseja:");
        System.out.println("\t0: Desfazer");
        System.out.println("\t1: Refazer");

        if (readEntries(0, 1) == 0) {
            return sysControl.undo();
        }
        else {
            return sysControl.redo();
        }
    }
}
