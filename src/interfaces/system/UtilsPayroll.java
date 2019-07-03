package interfaces.system;

import funcionabilities.Commisioned;
import funcionabilities.Employee;
import funcionabilities.Hourly;
import funcionabilities.Salaried;
import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.calendar.Calendar;
import funcionabilities.functional_aids.calendar.PointCalendar;
import funcionabilities.functional_aids.transactions.*;
import interfaces.SystemSettings;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.List;

class UtilsPayroll {

    private static Syndicate crateSindicate(List<Object> paramater) {
        Syndicate synd = null;
        if (SystemSettings.TYPE_SYNDICATES.get(paramater.get(0))[0] == 0) {
            synd = new Syndicate((String) paramater.get(1), (double) paramater.get(2));
        }

        return synd;
    }

    private static IMethodsPayments createMethods(List<Object> paramater) {
        IMethodsPayments meth = null;

        switch (SystemSettings.TYPE_METHODS_PAYMENTS.get(paramater.get(0))[0]) {
            case 0:
                meth = new Deposit((BankAcount) paramater.get(1), (Double) paramater.get(2),
                        (String) paramater.get(3));
                break;

            case 1:
                meth = new CheckHands((BankAcount) paramater.get(1), (Double) paramater.get(2),
                        (String) paramater.get(3), (int) paramater.get(4));
                break;

            case 2:
                meth = new CheckPostOffices((BankAcount) paramater.get(1), (Double) paramater.get(2),
                        (String) paramater.get(3), (String) paramater.get(4));
                break;

        }

        return meth;
    }

    static PaymentBills createTypePayment(List<Object> paramater) throws CloneNotSupportedException, InvalidAttributesException {
        PaymentBills type = null;
        switch (SystemSettings.TYPE_PAYMENTS.get(paramater.get(0))[0]) {
            case -1:
                type = (PaymentBills) paramater.get(1);
                break;

            case 0:
                type = new PaymentBills((Calendar) paramater.get(1), (int) paramater.get(2),
                        (int) paramater.get(3), (int) paramater.get(4));
                break;
        }

        return type;
    }

    private static PointCalendar createPoint(List<Object> paramater) {
        PointCalendar point = null;
        if (SystemSettings.TYPE_POINTS.get(paramater.get(0))[0] == 0) {
            point = new PointCalendar();
        }

        return point;
    }

    private static Employee createEmployee(List<Object> paramater, Syndicate synd, IMethodsPayments meth,
                                           PaymentBills type, PointCalendar point) throws Error {
        if (meth == null || type == null || point == null) throw new Error();
        Employee item;
        switch (SystemSettings.TYPE_EMPLOYEES.get(paramater.get(0))[0]) {
            case 0:
                item = new Hourly((String) paramater.get(1), (String) paramater.get(2), (int) paramater.get(3),
                        synd, meth, type, point, (int) paramater.get(4), (Double) paramater.get(5),
                        (Double) paramater.get(6));
                break;

            case 1:
                item = new Salaried((String) paramater.get(1), (String) paramater.get(2),
                        (int) paramater.get(3), synd, meth, type, point, (Double) paramater.get(4));

                break;

            case 2:
                item = new Commisioned((String) paramater.get(1), (String) paramater.get(2),
                        (int) paramater.get(3), synd, meth, type, point, (Double) paramater.get(4), (Double) paramater.get(5));
                break;

            default:
                throw new IllegalArgumentException();
        }

        return item;
    }

    static Employee processEmployee(ArrayList<ArrayList<Object>> paramater) throws InvalidAttributesException, CloneNotSupportedException {
        if (paramater.size() > 5) throw new Error();

        Syndicate synd = UtilsPayroll.crateSindicate(paramater.get(1));
        IMethodsPayments meth = UtilsPayroll.createMethods(paramater.get(2));
        PaymentBills type = UtilsPayroll.createTypePayment(paramater.get(3));
        PointCalendar point = UtilsPayroll.createPoint(paramater.get(4));

        return UtilsPayroll.createEmployee(paramater.get(0), synd, meth, type, point);
    }
}
