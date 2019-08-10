package view;

import controller.Payroll;
import controller.SystemController;

import static view.utils.UtilsSystem.readEntries;

public class Restore {
    public static Payroll execute() {
        SystemController sysControl = SystemController.getSysControl();
        System.out.println("You wish: ");
        System.out.println("\t0: Undo");
        System.out.println("\t1: Redo");

        if (readEntries(0, 1) == 0) {
            return sysControl.undo();
        }
        else {
            return sysControl.redo();
        }
    }
}
