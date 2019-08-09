package interfaces.system.memento;

import interfaces.system.Payroll;

import java.util.EmptyStackException;
import java.util.Stack;

public class Restore {
    private final Stack<Memento> restoreUndo;
    private final Stack<Memento> restoreRedo;

    public Restore() {
        restoreRedo = new Stack<>();
        restoreUndo = new Stack<>();
    }

    private Payroll unReDo(Payroll pay, Stack<Memento> restore1,
                                    Stack<Memento> restore2) throws Error {
        Payroll item;
        try {
            item = restore2.pop().getSavedState();
        } catch (EmptyStackException e) {
            System.out.println("\nIt's not possible, stack it's empty");
            return null;
        }

        try {
            restore1.push(new Memento(pay));
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            throw new Error("Problem trying to restore system.");
        }

        return item;
    }

    public Payroll undo(Payroll pay) {
        return unReDo(pay, restoreRedo, restoreUndo);
    }

    public Payroll redo(Payroll pay) {
        return unReDo(pay, restoreUndo, restoreRedo);
    }

    public void backup(Payroll item, boolean type) throws Error {
        try {
            if (type) {
                restoreUndo.push(new Memento(item));
            } else {
                restoreRedo.push(new Memento(item));
            }
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            throw new Error("Error in backup payroll.");
        }


    }
}
