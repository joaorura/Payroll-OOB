package interfaces.user.funcionabilities;

import interfaces.system.Payroll;
import interfaces.user.UtilsMain;

public class ChangeService implements Execute {
    @Override
    public void execute(Payroll payroll) {
        System.out.println("You desire retire or add services: \n" +
                "\t0: Add\n" +
                "\t1: Remove");

        int aux = UtilsMain.readEntries(0, 1);
        boolean type = (aux == 0);

        System.out.print("Name of product: ");
        UtilsMain.takeString();
        String name_product = UtilsMain.takeString();

        System.out.println("Value of product: ");
        Double value_product = (Double) UtilsMain.readEntries(Double.class);
        if (value_product == null) {
            throw new Error();
        }

        payroll.processServiceChange(type, UtilsMain.identification(),
                name_product, value_product);
    }
}
