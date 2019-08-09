package view.problematics;

import controller.EmployeeController;
import view.utils.UtilsEmployee;
import view.utils.UtilsSystem;
import view.Execute;

public class ChangeSale implements Execute {
    @Override
    public void execute() {
        EmployeeController empControl = EmployeeController.getMainEmpControl();

        System.out.println("\nProcess Sale Result\ns");
        System.out.print("\n\tName of product: ");
        UtilsSystem.takeString();
        String name_product = UtilsSystem.takeString();

        Double value_product;
        System.out.println("\n\tValue of product: ");
        do value_product = (Double) UtilsSystem.readEntries(Double.class); while (value_product == null);

        try {
            empControl.processSaleResult(UtilsEmployee.identifier(),
                    name_product, value_product);
        } catch (Error e) {
            System.out.println("Error in change sales of employee.");
        }
    }
}
