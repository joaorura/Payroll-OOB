package interfaces.user.funcionabilities;

import interfaces.system.Payroll;
import interfaces.system.controlers.SystemController;

import static interfaces.user.utils.UtilsSystem.readEntries;

public class Restore {
    public static Payroll execute(SystemController pay) {
        System.out.println("Você deseja:");
        System.out.println("\t0: Desfazer");
        System.out.println("\t1: Refazer");

        if (readEntries(0, 1) == 0) {
            return pay.undo();
        }
        else {
            return pay.redo();
        }
    }
}
