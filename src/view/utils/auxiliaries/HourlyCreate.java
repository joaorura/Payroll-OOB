package view.utils.auxiliaries;

import model.problematics.Employee;
import model.problematics.Hourly;
import view.problematics.UtilsProblematicCreate;
import view.utils.UtilsSystem;

import java.util.ArrayList;

public class HourlyCreate implements ExecuteCreate {
    private static final String MAXIMUM_HOURS = "Maximum hours to Work";
    private static final String OVER_WORK = "Tax to over work";
    private static final String RATIO = "Ratio Hour worked";

    @Override
    public void execute(boolean check, Employee emp, ArrayList<Object> param) {
        Hourly hAux = null;
        if (check) hAux = (Hourly) emp;

        param.set(0, Hourly.class);

        if (UtilsProblematicCreate.canChange(check, MAXIMUM_HOURS)) {
            System.out.print("\n" + MAXIMUM_HOURS + ": ");
            param.add(UtilsSystem.readEntries(Integer.class));
        } else {
            param.add(hAux.getMaxWorkHours());
        }

        if (UtilsProblematicCreate.canChange(check, OVER_WORK)) {
            System.out.print("\n" + OVER_WORK + ": ");
            param.add(UtilsSystem.readEntries(Double.class));
        } else {
            param.add(hAux.getTaxOverWork());
        }

        if (UtilsProblematicCreate.canChange(check, RATIO)) {
            System.out.println("\n" + RATIO + ": ");
            param.add(UtilsSystem.readEntries(Double.class));
        } else {
            param.add((hAux.getRatioWork()));
        }
    }
}
