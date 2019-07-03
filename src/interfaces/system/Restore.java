package interfaces.system;

import java.util.EmptyStackException;
import java.util.Stack;

class Restore {
    private final Stack<Payroll> restoreUndo;
    private final Stack<Payroll> restoreRedo;

    Restore() {
        restoreRedo = new Stack<>();
        restoreUndo = new Stack<>();
    }

    private Payroll unReDo(Stack<Payroll> restore1, Stack<Payroll> restore2) {
        Payroll item;
        try {
            item = restore2.pop();
        } catch (EmptyStackException e) {
            System.out.println("\nIt's not possible, stack it's empty");
            return null;
        }

        try {
            restore1.push(Payroll.getDefault().clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    Payroll undo() {
        return unReDo(restoreRedo, restoreUndo);
    }

    Payroll redo() {
        return unReDo(restoreUndo, restoreRedo);
    }

    void backup(Payroll item, boolean type) {
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
