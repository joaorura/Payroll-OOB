package interfaces.system;

import funcionabilities.Employee;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.payments.PaymentBills;
import interfaces.SystemSettings;

import java.util.GregorianCalendar;
import java.util.List;

public class UtilsPayroll {
    public static ITypePayments createTypePayment(List<Object> paramater) {
        ITypePayments type = null;
        switch (SystemSettings.TYPE_PAYMENTS.get(paramater.get(0))[0]) {
            case -1:
                type = (ITypePayments) paramater.get(1);
                break;

            case 0: type = new PaymentBills((GregorianCalendar) paramater.get(1), (int) paramater.get(2),
                    (int) paramater.get(3), (int) paramater.get(4));
                break;
        }

        return type;
    }

    public static void createPaymentSchedule(List<Object> param) {
        ITypePayments type_aux = createTypePayment(param);
        SystemSettings.DEFAULT_TYPESPAYMENTS.add(type_aux);
    }

    public static void setPaymentSchedule(int id, int aux) {
        Employee emp = Payroll.getDefault().searchEmployee(id);
        emp.setPersonalIPayment(SystemSettings.DEFAULT_TYPESPAYMENTS.get(aux).clone());
    }

    public static void setPaymentSchedule(String name, int aux) {
        setPaymentSchedule(Payroll.getDefault().searchEmployee(name), aux);
    }
}
