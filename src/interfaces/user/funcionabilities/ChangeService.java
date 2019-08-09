package interfaces.user.funcionabilities;

import interfaces.system.controlers.EmployeeController;
import interfaces.user.utils.UtilsEmployee;
import interfaces.user.utils.UtilsSystem;

public class ChangeService implements Execute {
    @Override
    public void execute() {
        EmployeeController empControl = EmployeeController.getMainEmpControl();
        System.out.println("You desire retire or add services: \n" +
                "\t0: Add\n" +
                "\t1: Remove");

        int aux = UtilsSystem.readEntries(0, 1);
        boolean type = (aux == 0);

        System.out.print("Name of product: ");
        UtilsSystem.takeString();
        String name_product = UtilsSystem.takeString();

        System.out.println("Value of product: ");
        Double value_product = (Double) UtilsSystem.readEntries(Double.class);
        if (value_product == null) {
            throw new Error();
        }

        empControl.processServiceChange(type, UtilsEmployee.identifier(),
                name_product, value_product);
    }
}
