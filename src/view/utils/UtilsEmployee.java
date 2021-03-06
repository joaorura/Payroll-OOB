package view.utils;

import model.problematics.Employee;
import model.problematics.Syndicate;
import model.problematics.PointCalendar;
import controller.Payroll;
import controller.EmployeeController;
import view.problematics.UtilsProblematicCreate;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

import static view.utils.UtilsSystem.readEntries;

public class UtilsEmployee {
    public static int identifier() {
        EmployeeController empControl = EmployeeController.getMainEmpControl();
        UtilsSystem.printIdentification();

        int type_id = readEntries(0, 1);
        int id;

        if (type_id == 0) {
            System.out.print("Identifier: ");
            id = readEntries(0, Integer.MAX_VALUE);
        } else {
            System.out.print("Name: ");
            id = empControl.searchEmployee(UtilsSystem.takeString());
        }

        if (empControl.searchEmployee(id) == null) {
            System.out.println("Employee not founded");
            return -1;
        }
        else {
            return id;
        }
    }

    public static ArrayList<ArrayList<Object>> getData(Employee emp)
            throws InvalidAttributesException {
        EmployeeController empControl = EmployeeController.getMainEmpControl();
        ArrayList<ArrayList<Object>> param = new ArrayList<>();
        ArrayList<Object> auxOb;

        for (int i = 0; i < 5; i++) {
            param.add(new ArrayList<>());
        }

        boolean check = emp != null;
        UtilsCreate.identifierProcess(empControl, emp, param.get(0));

        if (UtilsProblematicCreate.canChange(check, "Syndicate")) {
            UtilsProblematicCreate.syndicateProcess(param.get(1));
        } else {
            Syndicate syndicate = emp.getSyndicate();
            auxOb = param.get(1);

            if (syndicate == null) {
                auxOb.add(-1);
            } else {
                auxOb.add(syndicate.getIdentifier());
                auxOb.add(syndicate.getMonthlyFee());
            }
        }

        UtilsProblematicCreate.methodProcess(empControl, (String) param.get(0).get(1),
                (String) param.get(0).get(2), param.get(2));
        UtilsProblematicCreate.typeProcess(Payroll.getMainPayroll(), false, param.get(3));

        param.get(4).add(PointCalendar.class);

        return param;
    }
}
