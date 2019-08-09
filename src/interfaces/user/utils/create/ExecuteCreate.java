package interfaces.user.utils.create;

import model.problematics.Employee;

import java.util.ArrayList;

public interface ExecuteCreate {
    void execute(boolean check, Employee emp, ArrayList<Object> param);
}
