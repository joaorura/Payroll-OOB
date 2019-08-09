package interfaces.user.utils.create;

import model.Employee;
import model.Hourly;
import interfaces.user.utils.UtilsSystem;

import java.util.ArrayList;

import static interfaces.user.problematics.UtilsProblematicCreate.canChange;

public class HourlyCreate implements ExecuteCreate {
    private static final String MHOURS = "Maximum hours to Work";
    private static final String OVERW = "Tax to over work";
    private static final String RATIO = "Ratio Hour worked";

    @Override
    public void execute(boolean check, Employee emp, ArrayList<Object> param) {
        Hourly hAux = null;
        if (check) hAux = (Hourly) emp;

        param.set(0, Hourly.class);

        if (canChange(check, MHOURS)) {
            System.out.print("\n" + MHOURS + ": ");
            param.add(UtilsSystem.readEntries(Integer.class));
        } else {
            param.add(hAux.getMaxWorkHours());
        }

        if (canChange(check, OVERW)) {
            System.out.print("\n" + OVERW + ": ");
            param.add(UtilsSystem.readEntries(Double.class));
        } else {
            param.add(hAux.getTaxOverWork());
        }

        if (canChange(check, RATIO)) {
            System.out.println("\n" + RATIO + ": ");
            param.add(UtilsSystem.readEntries(Double.class));
        } else {
            param.add((hAux.getRatioWork()));
        }
    }
}
