package controller;

import model.*;
import controller.Payroll;
import model.problematics.*;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.List;

class UtilsPayroll {
    private static void errorCreate() {
        throw new Error("Error in instantiate a employee");
    }

    private static Syndicate crateSindicate(List<Object> paramater) throws Error {
        Syndicate synd = null;
        int checker = (int) paramater.get(0);
        if(checker == -1) {
            errorCreate();
        }
        else if (checker == 0) {
            synd = new Syndicate((String) paramater.get(1), (double) paramater.get(2));
        }

        return synd;
    }

    private static IMethodsPayments createMethods(Payroll payroll, List<Object> paramater) {
        IMethodsPayments meth = null;
        BankAccount bank = (BankAccount) paramater.get(1);
        if(bank == null) {
            bank = payroll.getAccountCompany();
        }

        switch ((int) paramater.get(0)) {
            case 0:
                meth = new Deposit(bank, (Double) paramater.get(3),
                        (String) paramater.get(4));
                break;

            case 1:
                meth = new CheckHands(bank, (Double) paramater.get(3),
                        (String) paramater.get(4), (int) paramater.get(5));
                break;

            case 2:
                meth = new CheckPostOffices(bank, (Double) paramater.get(3),
                        (String) paramater.get(4), (String) paramater.get(5));
                break;

        }

        return meth;
    }

    static PaymentBills createTypePayment(List<Object> paramater) throws CloneNotSupportedException, InvalidAttributesException {
        PaymentBills type = null;
        switch ((int) paramater.get(0)) {
            case 0:
                type = new PaymentBills((Calendar) paramater.get(1), (int) paramater.get(2),
                        (int) paramater.get(3), (int) paramater.get(4));
                break;

            case 1:
                type = (PaymentBills) paramater.get(1);
                break;
        }

        return type;
    }

    private static PointCalendar createPoint(List<Object> paramater) {
        PointCalendar point = null;
        if ((int) paramater.get(0) == 0) {
            point = new PointCalendar();
        }

        return point;
    }

    private static Employee createEmployee(List<Object> paramater, Syndicate synd, IMethodsPayments meth,
                                           PaymentBills type, PointCalendar point) throws Error {
        if (meth == null || type == null || point == null) throw new Error();
        Employee item;
        switch ((int) paramater.get(0)) {
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
                item = new Commissioned((String) paramater.get(1), (String) paramater.get(2),
                        (int) paramater.get(3), synd, meth, type, point, (Double) paramater.get(4), (Double) paramater.get(5));
                break;

            default:
                throw new IllegalArgumentException();
        }

        return item;
    }

    static Employee processEmployee(Payroll payroll, ArrayList<ArrayList<Object>> paramater) throws InvalidAttributesException, CloneNotSupportedException {
        if (paramater.size() > 5) throw new Error();

        Syndicate synd = crateSindicate(paramater.get(1));
        IMethodsPayments meth = createMethods(payroll, paramater.get(2));
        PaymentBills type = createTypePayment(paramater.get(3));
        PointCalendar point = createPoint(paramater.get(4));

        return createEmployee(paramater.get(0), synd, meth, type, point);
    }
}