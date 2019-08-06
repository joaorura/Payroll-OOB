package interfaces.user.funcionabilities.problematics;

import interfaces.system.controlers.EmployeeController;
import interfaces.user.UtilsMain;
import interfaces.user.funcionabilities.ExecuteEmp;

public class ChangeSale implements ExecuteEmp {
    @Override
    public void execute(EmployeeController payroll) {
        System.out.println("\nProcess Sale Result\ns");
        System.out.print("\n\tName of product: ");
        UtilsMain.takeString();
        String name_product = UtilsMain.takeString();

        Double value_product;
        System.out.println("\n\tValue of product: ");
        do value_product = (Double) UtilsMain.readEntries(Double.class); while (value_product == null);

        try {
            payroll.processSaleResult(UtilsMain.identification(payroll),
                    name_product, value_product);
        } catch (Error e) {
            System.out.println("Error in change sales of employee.");
        }
    }
}
