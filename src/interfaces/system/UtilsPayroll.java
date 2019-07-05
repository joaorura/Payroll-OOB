package interfaces.system;

import funcionabilities.Employee;
import funcionabilities.functional_aids.calendar.Calendar;
import funcionabilities.functional_aids.PaymentBills;
import interfaces.SystemSettings;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;

public class UtilsPayroll {
    private static PaymentBills createTypePayment(List<Object> paramater) {
        PaymentBills type = null;
        switch (SystemSettings.TYPE_PAYMENTS.get(paramater.get(0))[0]) {
            case -1:
                type = (PaymentBills) paramater.get(1);
                break;

            case 0:
                try {
                    type = new PaymentBills((Calendar) paramater.get(1), (int) paramater.get(2),
                            (int) paramater.get(3), (int) paramater.get(4));
                } catch (InvalidAttributesException | CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                break;
        }

        return type;
    }

    public static void createPaymentSchedule(List<Object> param) {
        PaymentBills type_aux = createTypePayment(param);
        SystemSettings.DEFAULT_TYPESPAYMENTS.add(type_aux);
    }

    private static void setPaymentSchedule(int id, int aux) {
        Employee emp = Payroll.getDefault().searchEmployee(id);
        try {
            emp.setPersonalIPayment(SystemSettings.DEFAULT_TYPESPAYMENTS.get(aux).clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static void setPaymentSchedule(String name, int aux) {
        setPaymentSchedule(Payroll.getDefault().searchEmployee(name), aux);
    }
}
