package interfaces.user;

import funcionabilities.Commisioned;
import funcionabilities.Employee;
import funcionabilities.Hourly;
import funcionabilities.Salaried;
import interfaces.system.Payroll;

import java.util.ArrayList;

import static interfaces.user.problematics.UtilsProblematicCreate.canChange;

class UtilsCreate {
    static void identificatonProcess(Employee emp, ArrayList<Object> param) {
        boolean check = emp != null;
        param.add(null);

        if (canChange(check, "name")) {
            System.out.print("\tName: ");
            param.add(UtilsMain.takeString());
        } else {
            param.add(emp.getName());
        }

        if (canChange(check, "adress")) {
            System.out.print("\tAdress:");
            String str = UtilsMain.takeString();

            while (str.equals("\n")) {
                System.out.print("Digite novamente: ");
                str = UtilsMain.takeString();
            }

            param.add(str);
        }

        int aux;
        if (!check) {
            param.add(Payroll.getDefault().nextId());

            System.out.println("\nType:\n" +
                    "\t0: Hourly\n" +
                    "\t1: Salaried\n" +
                    "\t2 : Commissioned\n");

            aux = UtilsMain.readEntries(0, 2);
        } else {
            param.add(emp.getId());

            if (emp instanceof Hourly) aux = 0;
            else if (emp instanceof Commisioned) aux = 2;
            else aux = 1;
        }


        switch (aux) {
            case 0:
                Hourly hAux = null;
                if (check) hAux = (Hourly) emp;

                param.set(0, Hourly.class);

                if (canChange(check, "Maximum hours to Work")) {
                    System.out.print("\nMaximun hours to work: ");
                    param.add(UtilsMain.readEntries(Integer.class));
                } else {
                    param.add(hAux.getMaxWorkHours());
                }

                if (canChange(check, "Tax to over work")) {
                    System.out.print("\nTax to over work: ");
                    param.add(UtilsMain.readEntries(Double.class));
                } else {
                    param.add(hAux.getTaxOverWork());
                }

                if (canChange(check, "Ratio Hour woked")) {
                    System.out.println("\nRatio Hour woked: ");
                    param.add(UtilsMain.readEntries(Double.class));
                } else {
                    param.add((hAux.getRatioWork()));
                }

                break;

            case 1:
                Salaried sAux = null;
                if (check) sAux = (Salaried) emp;

                param.set(0, Salaried.class);

                if (canChange(check, "Salary")) {
                    System.out.println("\n\nSalary: ");
                    param.add(UtilsMain.readEntries(Double.class));
                } else {
                    param.add(sAux.getSalary());
                }

                break;

            case 2:
                Commisioned cAux = null;
                if (check) cAux = (Commisioned) emp;

                param.set(0, Commisioned.class);

                if (canChange(check, "Salary")) {
                    System.out.println("\n\nSalary: ");
                    param.add(UtilsMain.readEntries(Double.class));
                } else {
                    param.add(cAux.getSalary());
                }

                if (canChange(check, "Ratio sales")) {
                    System.out.println("\n\nRatio sales");
                    param.add(UtilsMain.readEntries(Double.class));
                } else {
                    param.add(cAux.getRatioSales());
                }

                break;
        }
    }
}
