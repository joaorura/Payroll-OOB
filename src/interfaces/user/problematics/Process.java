package interfaces.user.problematics;

import interfaces.system.Payroll;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.*;
import interfaces.user.funcionabilities.problematics.*;

public class Process {
    private static Execute processExecution(int input) {
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

            case 9:
                return new Restore();
        }

        throw new RuntimeException("Input integer take over a value out of domain (0 to 9).");
    }

    public static boolean processEntries(int input) {
        System.out.println("\nStarting the operation ...\n");
        Payroll pay = Payroll.getDefault();
        int id;

        if (input != 9 && input  != 1) {
            if (input != 0 && input != 7 && input != 8) {
                id = UtilsMain.identification();
                if(id == -1) {
                    System.out.println("Employee not founded");
                }
            }
            pay.backup(true);
        }

        Execute exec;

        try {
           exec = processExecution(input);
        }
        catch (RuntimeException e) {
            System.out.println("Error in system: " + e.getMessage() + "\n" +
                    "The system will be close.");

            return false;
        }

        exec.execute(pay);
        return true;
    }
}
