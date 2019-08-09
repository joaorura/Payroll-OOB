package view.utils.auxiliaries;

import model.problematics.Employee;
import model.problematics.Hourly;
import view.problematics.UtilsProblematicCreate;
import view.utils.UtilsSystem;

import java.util.ArrayList;

public class HourlyCreate implements ExecuteCreate {
    private static final String MHOURS = "Maximum hours to Work";
    private static final String OVERW = "Tax to over work";
    private static final String RATIO = "Ratio Hour worked";

    @Override
    public void execute(boolean check, Employee emp, ArrayList<Object> param) {
        Hourly hAux = null;
        if (check) hAux = (Hourly) emp;

        param.set(0, Hourly.class);

        if (UtilsProblematicCreate.canChange(check, MHOURS)) {
            System.out.print("\n" + MHOURS + ": ");
            param.add(UtilsSystem.readEntries(Integer.class));
        } else {
            param.add(hAux.getMaxWorkHours());
        }

        if (UtilsProblematicCreate.canChange(check, OVERW)) {
            System.out.print("\n" + OVERW + ": ");
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
