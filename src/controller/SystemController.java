package controller;

import model.problematics.Employee;
import model.problematics.PaymentBills;
import model.problematics.Calendar;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class SystemController {
    private static final SystemController sysControl = new SystemController(Payroll.getMainPayroll());

    private Payroll pay;

    private static final String errorRunPay = "Error in run payroll";

    private SystemController(Payroll pay) {
        this.pay =  pay;
    }

    public Payroll undo() {
        return pay.getBackup().undo(pay);
    }

    public Payroll redo() {
        return pay.getBackup().redo(pay);
    }

    public void runPayrollToday() throws Error{
        System.out.println(pay.getActualCalendar().toString());

        for (int i = 0; i < 1440; i++) {

            try {
                for (Employee employee : pay.getEmployees()) {
                    if (employee.getPersonalIPayment().checkItsDay(pay.getActualCalendar())) {
                        employee.attMoney();
                        System.out.println(employee.getName() + "\n" + employee.getMethodPayment().toString());
                    }
                }
            }
            catch (NullPointerException e) {
                System.out.println("Without employees\n");
                throw new Error(errorRunPay);
            }


            try {
                pay.getActualCalendar().add(Calendar.MINUTE, 1);
            } catch (InvalidAttributesException e) {
                throw new Error(errorRunPay);
            }
        }
    }

    public void createEmployeePaymentSchedule(Payroll pay, ArrayList<Object> param) throws Error{
        try {
            PaymentBills pb = UtilsPayroll.createTypePayment(param);
            pay.addEmployeeSchedule(pb);

        } catch (CloneNotSupportedException | InvalidAttributesException e) {
            throw new Error("Error in create a employee schedule.");
        }
    }

    public static SystemController getSysControl() {
        return sysControl;
    }

    public void reconfigure() {
        this.pay = Payroll.getMainPayroll();

    }
}
