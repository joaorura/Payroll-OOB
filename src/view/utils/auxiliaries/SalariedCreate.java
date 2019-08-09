package view.utils.auxiliaries;

import model.problematics.Employee;
import model.problematics.Salaried;
import view.problematics.UtilsProblematicCreate;
import view.utils.UtilsSystem;

import java.util.ArrayList;

public class SalariedCreate implements ExecuteCreate {
    private static final String SALARY = "Salary";

    @Override
    public void execute(boolean check, Employee emp, ArrayList<Object> param) {
        Salaried sAux = null;
        if (check) sAux = (Salaried) emp;

        param.set(0, Salaried.class);

        if (UtilsProblematicCreate.canChange(check, SALARY)) {
            System.out.println("\n\n" + SALARY + ": ");
            param.add(UtilsSystem.readEntries(Double.class));
        } else {
            param.add(sAux.getSalary());
        }
    }
}
