package interfaces.system;

import java.util.EmptyStackException;
import java.util.Stack;

public class Restore implements IMemento<Payroll> {
    private Stack<Payroll> restoreUndo;
    private Stack<Payroll> restoreRedo;

    Restore() {
        restoreRedo = new Stack<>();
        restoreUndo = new Stack<>();
    }

    public Payroll undo() {
        try {
            restoreRedo.push(Payroll.getDefault().clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Payroll item;
        try {
            item = restoreUndo.pop();
        }catch (EmptyStackException e) {
            System.out.println("It's not possible, stack it's empty");
            return null;
        }

        return item;
    }

    public Payroll redo() {
        try {
            restoreUndo.push(Payroll.getDefault().clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Payroll item = restoreRedo.pop();
        if(item == null) throw new Error("It's not possible, stack it's empty");

        return item;
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
