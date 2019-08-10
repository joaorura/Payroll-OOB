package controller;

import model.*;
import model.problematics.*;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.List;

class UtilsPayroll {
    private static void errorCreate() {
        throw new Error("Error in instantiate a employee");
    }

    private static Syndicate crateSyndicate(List<Object> parameters) throws Error {
        Syndicate syndicate = null;
        int checker = (int) parameters.get(0);
        if(checker == -1) {
            errorCreate();
        }
        else if (checker == 0) {
            syndicate = new Syndicate((String) parameters.get(1), (double) parameters.get(2));
        }

        return syndicate;
    }

    private static IMethodsPayments createMethods(Payroll payroll, List<Object> parameters) {
        IMethodsPayments methodPayments = null;
        BankAccount bank = (BankAccount) parameters.get(1);
        if(bank == null) {
            bank = payroll.getAccountCompany();
        }

        switch ((int) parameters.get(0)) {
            case 0:
                methodPayments = new Deposit(bank, (Double) parameters.get(3),
                        (String) parameters.get(4));
                break;

            case 1:
                methodPayments = new CheckHands(bank, (Double) parameters.get(3),
                        (String) parameters.get(4), (int) parameters.get(5));
                break;

            case 2:
                methodPayments = new CheckPostOffices(bank, (Double) parameters.get(3),
                        (String) parameters.get(4), (String) parameters.get(5));
                break;

        }

        return methodPayments;
    }

    static PaymentBills createTypePayment(List<Object> parameters) throws CloneNotSupportedException, InvalidAttributesException {
        PaymentBills type = null;
        switch ((int) parameters.get(0)) {
            case 0:
                type = new PaymentBills((Calendar) parameters.get(1), (int) parameters.get(2),
                        (int) parameters.get(3), (int) parameters.get(4));
                break;

            case 1:
                type = (PaymentBills) parameters.get(1);
                break;
        }

        return type;
    }

    private static PointCalendar createPoint(List<Object> parameters) {
        PointCalendar point = null;
        if ((int) parameters.get(0) == 0) {
            point = new PointCalendar();
        }

        return point;
    }

    private static Employee createEmployee(List<Object> parameters, Syndicate syndicate, IMethodsPayments methodsPayments,
                                           PaymentBills type, PointCalendar point) throws Error {
        if (methodsPayments == null || type == null || point == null) throw new Error();
        Employee item;
        switch ((int) parameters.get(0)) {
            case 0:
                item = new Hourly((String) parameters.get(1), (String) parameters.get(2), (int) parameters.get(3),
                        syndicate, methodsPayments, type, point, (int) parameters.get(4), (Double) parameters.get(5),
                        (Double) parameters.get(6));
                break;

            case 1:
                item = new Salaried((String) parameters.get(1), (String) parameters.get(2),
                        (int) parameters.get(3), syndicate, methodsPayments, type, point, (Double) parameters.get(4));

                break;

            case 2:
                item = new Commissioned((String) parameters.get(1), (String) parameters.get(2),
                        (int) parameters.get(3), syndicate, methodsPayments, type, point, (Double) parameters.get(4), (Double) parameters.get(5));
                break;

            default:
                throw new IllegalArgumentException();
        }

        return item;
    }

    static Employee processEmployee(ArrayList<ArrayList<Object>> parameters) throws InvalidAttributesException, CloneNotSupportedException {
        Payroll payroll = Payroll.getMainPayroll();

        if (parameters.size() > 5) throw new Error();

        Syndicate syndicate = crateSyndicate(parameters.get(1));
        IMethodsPayments methods = createMethods(payroll, parameters.get(2));
        PaymentBills type = createTypePayment(parameters.get(3));
        PointCalendar point = createPoint(parameters.get(4));

        return createEmployee(parameters.get(0), syndicate, methods, type, point);
    }
}