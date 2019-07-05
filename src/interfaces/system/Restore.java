package interfaces.system;

import java.util.EmptyStackException;
import java.util.Stack;

public class Restore {
    private Stack<Payroll> restoreUndo;
    private Stack<Payroll> restoreRedo;

    Restore() {
        restoreRedo = new Stack<>();
        restoreUndo = new Stack<>();
    }

    public Payroll undo() {
        return getPayroll(restoreRedo, restoreUndo);
    }

    public Payroll redo() {
        return getPayroll(restoreUndo, restoreRedo);
    }

    private Payroll getPayroll(Stack<Payroll> restoreUndo, Stack<Payroll> restoreRedo) {
        try {
            restoreUndo.push(Payroll.getDefault().clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Payroll redo;
        try {
            redo = restoreRedo.pop();
        }catch (EmptyStackException e) {
            System.out.println("It's not possible, stack it's empty");
            return null;
        }

        return redo;
    }

    public void backup(Payroll rest, boolean type) {
        Payroll item = rest;
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
