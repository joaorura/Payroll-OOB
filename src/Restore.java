import memento.IMemento;

import java.util.Stack;

public class Restore implements IMemento<Payroll> {
    private Stack<Payroll> restoreUndo;
    private Stack<Payroll> restoreRedo;


    Restore() {
        restoreRedo = new Stack<>();
        restoreRedo = new Stack<>();
    }

    public Payroll undo() {
        Payroll item = restoreUndo.pop();
        try {
            restoreRedo.push(item.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    public Payroll redo() {
        Payroll item = restoreRedo.pop();
        try {
            restoreUndo.push(item.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    public boolean backup(Payroll rest, boolean type) {
        Payroll item = rest;
        try {
            item = item.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if (type) {
            restoreRedo.push(item);
        } else {
            restoreUndo.push(item);
        }

        return true;
    }
}
