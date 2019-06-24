package funcionabilities;

import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.payments.PaymentBills;
import funcionabilities.functional_aids.transactions.Check;
import funcionabilities.functional_aids.transactions.CheckHands;
import funcionabilities.functional_aids.transactions.Deposit;

import java.util.GregorianCalendar;

public class SystemSettings {
    private static final GregorianCalendar startCalendar = (GregorianCalendar) GregorianCalendar.getInstance();

    private static final Class[] TYPES_METHODS = new Class[] {Deposit.class, CheckHands.class, Check.class};
    private static final Class[] TYPES_TYPESPAYMENTS = new Class[] {PaymentBills.class};
    private static final Class[] EMPLOYEE_STANDARDS =  new Class[] {Employee.class};

    private static final ITypePayments[] DEFAULT_TYPESPAYMENTS = new ITypePayments[] {
            new PaymentBills(5, 1, 0),
            new PaymentBills(-1, 0, 0),
            new PaymentBills(5, 2, 0)};
}
