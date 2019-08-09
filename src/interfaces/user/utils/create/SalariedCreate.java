package interfaces.user.utils.create;

import model.problematics.Employee;
import model.problematics.Salaried;
import interfaces.user.utils.UtilsSystem;

import java.util.ArrayList;

import static interfaces.user.problematics.UtilsProblematicCreate.canChange;

public class SalariedCreate implements ExecuteCreate {
    private static final String SALA = "Salary";

    @Override
    public void execute(boolean check, Employee emp, ArrayList<Object> param) {
        Salaried sAux = null;
        if (check) sAux = (Salaried) emp;

        param.set(0, Salaried.class);

        if (canChange(check, SALA)) {
            System.out.println("\n\n" + SALA + ": ");
            param.add(UtilsSystem.readEntries(Double.class));
        } else {
            param.add(sAux.getSalary());
        }
    }
}
