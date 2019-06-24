package memento;

import main.Employee;

import java.util.Stack;

public class Restore implements IMemento{
    private Stack<Employee> restoreUndo;
    private Stack<Employee> restoreRedo;

    public Employee undo() {
        Employee item = restoreUndo.pop();
        restoreRedo.push(item.clone());

        return item;
    }

    public Employee redo() {
        Employee item = restoreRedo.pop();
        restoreUndo.push(item.clone());

        return item;
    }

    public boolean backup(Object rest, boolean type) {
        Employee item = (Employee) rest;

        if(type) {
            restoreRedo.push(item.clone());
        }
        else {
            restoreUndo.push(item.clone());
        }

        return true;
    }
}
