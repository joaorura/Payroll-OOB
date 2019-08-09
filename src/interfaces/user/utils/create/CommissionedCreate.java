package interfaces.user.utils.create;

import model.Commissioned;
import model.Employee;
import interfaces.user.utils.UtilsSystem;

import java.util.ArrayList;

import static interfaces.user.problematics.UtilsProblematicCreate.canChange;

public class CommissionedCreate implements ExecuteCreate {
    private static final String SALARY = "Salary";
    private static final String RATIO = "Ratio sales";

    @Override
    public void execute(boolean check, Employee emp, ArrayList<Object> param) {
        Commissioned cAux = null;
        if (check) cAux = (Commissioned) emp;

        param.set(0, Commissioned.class);

        if (canChange(check, SALARY)) {
            System.out.println("\n\n" + SALARY + ": ");
            param.add(UtilsSystem.readEntries(Double.class));
        } else {
            param.add(cAux.getSalary());
        }

        if (canChange(check, RATIO)) {
            System.out.println("\n\n" + RATIO + ": ");
            param.add(UtilsSystem.readEntries(Double.class));
        } else {
            param.add(cAux.getRatioSales());
        }
    }
}
