package memento;


import funcionabilities.Payroll;

import java.util.Stack;

public class Restore implements IMemento {
    private Stack<Payroll> restoreUndo;
    private Stack<Payroll> restoreRedo;


    public Restore() {
        restoreRedo = new Stack<>();
        restoreRedo = new Stack<>();
    }

    public Payroll undo() {
        Payroll item = restoreUndo.pop();
        try {
            restoreRedo.push((Payroll) item.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    public Payroll redo() {
        Payroll item = restoreRedo.pop();
        try {
            restoreUndo.push((Payroll) item.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    public boolean backup(Object rest, boolean type) {
        Payroll item = (Payroll) rest;
        try {
            item = (Payroll) item.clone();
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
