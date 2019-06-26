import funcionabilities.Commisioned;
import funcionabilities.Employee;
import funcionabilities.Hourly;
import funcionabilities.Salaried;
import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.calendar.PointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.payments.PaymentBills;
import funcionabilities.functional_aids.transactions.*;

import java.util.*;

public class SystemSettings {
    public  static final GregorianCalendar START_CALENDAR = (GregorianCalendar) GregorianCalendar.getInstance();

    static final BankAcount ACOUNT = new BankAcount("ComapayName","00000000-1", "00.000000/0000-00");

    static final int MAX_PARAMATERS = 5;

    static final Map<Class, Integer[]> TYPE_SYNDICATES = Map.of(Class.class, new Integer[]{-1,0},
            Syndicate.class, new Integer[]{0, 2});
    static final Map<Class, Integer[]> TYPE_METHODS_PAYMENTS = Map.of(Deposit.class, new Integer[]{0, 3},
            CheckHands.class, new Integer[]{1, 4},
            CheckPostOffices.class, new Integer[]{2, 4});
    static final Map<Class, Integer[]> TYPE_PAYMENTS = Map.of(ITypePayments.class, new Integer[]{-1,1},
            PaymentBills.class, new Integer[]{0, 4});
    static final Map<Class, Integer[]> TYPE_POINTS = Map.of(PointCalendar.class, new Integer[]{0, 1});
    static final Map<Class, Integer[]> TYPE_EMPLOYEES = Map.of(Hourly.class, new Integer[]{0,10},
            Salaried.class, new Integer[]{1,8},
            Commisioned.class, new Integer[]{2,9});



    static final ITypePayments[] DEFAULT_TYPESPAYMENTS = new ITypePayments[] {
            new PaymentBills(5, 1, 0),
            new PaymentBills(-1, 0, 0),
            new PaymentBills(5, 2, 0)};
}