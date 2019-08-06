package interfaces.system;

import java.util.EmptyStackException;
import java.util.Stack;

public class Restore {
    private final Stack<Payroll> restoreUndo;
    private final Stack<Payroll> restoreRedo;

    public Restore() {
        restoreRedo = new Stack<>();
        restoreUndo = new Stack<>();
    }

    private Payroll unReDo(Payroll pay, Stack<Payroll> restore1,
                                    Stack<Payroll> restore2) {
        Payroll item;
        try {
            item = restore2.pop();
        } catch (EmptyStackException e) {
            System.out.println("\nIt's not possible, stack it's empty");
            return null;
        }

        try {
            restore1.push(pay.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    public Payroll undo(Payroll pay) {
        return unReDo(pay, restoreRedo, restoreUndo);
    }

    public Payroll redo(Payroll pay) {
        return unReDo(pay, restoreUndo, restoreRedo);
    }

    public void backup(Payroll item, boolean type) {
        try {
            item = item.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if (type) {
            restoreUndo.push(item);
        } else {
            restoreRedo.push(item);
        }

    }
}
