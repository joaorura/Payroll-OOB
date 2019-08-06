package interfaces.system.controlers;

import funcionabilities.Employee;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.calendar.Calendar;
import interfaces.SystemSettings;
import interfaces.system.Payroll;
import interfaces.system.utils.UtilsPayroll;
import interfaces.user.problematics.UtilsProblematicCreate;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class SystemController {
    Payroll pay;

    public SystemController(Payroll pay) {
        this.pay =  pay;
    }

    public Payroll undo() {
        return pay.backup.undo(pay);
    }

    public Payroll redo() {
        return pay.backup.redo(pay);
    }

    public void runPayrollToday() throws InvalidAttributesException {
        System.out.println(pay.actualCalendar.toString());

        for (int i = 0; i < 1440; i++) {

            try {
                for (Employee employee : pay.employees) {
                    if (employee.getPersonalIPayment().checkItsDay(pay.actualCalendar)) {
                        employee.attMoney();
                        System.out.println(employee.getName() + "\n" + employee.getMethodPayment().toString());
                    }
                }
            }
            catch (NullPointerException e) {
                System.out.println("Without employees\n");
                return;
            }


            pay.actualCalendar.add(Calendar.MINUTE, 1);
        }
    }

    public void createEmployeePaymentSchedule() {
        ArrayList<Object> param = new ArrayList<>();
        try {
            //Modularizar
            UtilsProblematicCreate.typeProcess(true, param);
        } catch (InvalidAttributesException e) {
            e.printStackTrace();
        }

        try {
            PaymentBills pb = UtilsPayroll.createTypePayment(param);
            SystemSettings.DEFAULT_TYPE_PAYMENTS.add(pb);

        } catch (CloneNotSupportedException | InvalidAttributesException e) {
            throw new RuntimeException();
        }

    }
}
