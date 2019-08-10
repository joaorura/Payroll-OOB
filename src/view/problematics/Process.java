package view.problematics;

import controller.Payroll;
import view.*;
import view.utils.UtilsEmployee;

public class Process {
    private static Execute processExecution(int input) throws  Error {
        switch (input) {
            case 0:
                return new AddEmployee();

            case 1:
                return new RemoveEmployee();

            case 2:
                return new ChangeEmployeeDetails();

            case 3:
                return new ChangePoint();

            case 4:
                return new ChangeSale();

            case 5:
                return new ChangeService();

            case 6:
                return new PrintState();

            case 7:
                return new RunPay();

            case 8:
                return new ChangePayment();
        }

        throw new Error("Error in process entries. Wrong input.");
    }

    public static boolean processEntries(int input) {
        System.out.println("\nStarting the operation ...\n");
        int id;
        if (input != 6 && input  != 9) {
            if (input != 0 && input != 7 && input != 8) {
                id = UtilsEmployee.identifier();
                if(id == -1) {
                    System.out.println("Employee not founded");
                    return false;
                }
            }
            Payroll.getMainPayroll().backup(true);
        }

        try {
            processExecution(input).execute();
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
