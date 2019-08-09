package interfaces.user.problematics;

import interfaces.system.Payroll;
import interfaces.system.controlers.EmployeeController;
import interfaces.system.controlers.SystemController;
import interfaces.user.utils.UtilsEmployee;
import interfaces.user.funcionabilities.*;
import interfaces.user.funcionabilities.problematics.*;

import java.util.Objects;

public class Process {
    private static ExecuteEmp processExecution(int input) {
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
        }

        return null;
    }

    public static boolean processEntries(Payroll pay, SystemController sysControll, EmployeeController empControll, int input) {
        System.out.println("\nStarting the operation ...\n");
        int id;
        if (input != 9 && input  != 1) {
            if (input != 0 && input != 7 && input != 8) {
                id = UtilsEmployee.identifier(empControll);
                if(id == -1) {
                    System.out.println("Employee not founded");
                    return false;
                }
            }
            pay.backup(true);
        }


        switch (input) {
            case 7:
                RunPay.execute(sysControll);

            case 8:
                ChangePayment.execute(empControll, sysControll);

            default:
                Objects.requireNonNull(processExecution(input)).execute(empControll);
        }

        return true;
    }
}
