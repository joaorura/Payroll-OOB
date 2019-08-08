package interfaces.user.utils.create;

import funcionabilities.Commisioned;
import funcionabilities.Employee;
import interfaces.user.utils.UtilsSystem;

import java.util.ArrayList;

import static interfaces.user.problematics.UtilsProblematicCreate.canChange;

public class CommisionedCreate implements ExecuteCreate {
    private static final String SALA = "Salary";
    private static final String RATIO = "Ratio sales";

    @Override
    public void execute(boolean check, Employee emp, ArrayList<Object> param) {
        Commisioned cAux = null;
        if (check) cAux = (Commisioned) emp;

        param.set(0, Commisioned.class);

        if (canChange(check, SALA)) {
            System.out.println("\n\n" + SALA + ": ");
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
