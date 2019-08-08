package interfaces;

import funcionabilities.Commisioned;
import funcionabilities.Hourly;
import funcionabilities.Salaried;
import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.calendar.PointCalendar;
import funcionabilities.functional_aids.transactions.BankAccount;
import funcionabilities.functional_aids.transactions.CheckHands;
import funcionabilities.functional_aids.transactions.CheckPostOffices;
import funcionabilities.functional_aids.transactions.Deposit;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.Map;

public final class SystemSettings {
    public static final BankAccount ACOUNT = new BankAccount("ComapayName", "00000000-1", "00.000000/0000-00");

    public static final Map<Class, Integer[]> TYPE_SYNDICATES = Map.of(Class.class, new Integer[]{-1, 0},
            Syndicate.class, new Integer[]{0, 2});
    public static final Map<Class, Integer[]> TYPE_METHODS_PAYMENTS = Map.of(Deposit.class, new Integer[]{0, 3},
            CheckHands.class, new Integer[]{1, 4},
            CheckPostOffices.class, new Integer[]{2, 4});
    public static final Map<Class, Integer[]> TYPE_PAYMENTS = Map.of(Class.class, new Integer[]{-1, 1},
            PaymentBills.class, new Integer[]{0, 4});
    public static final Map<Class, Integer[]> TYPE_POINTS = Map.of(PointCalendar.class, new Integer[]{0, 1});
    public static final Map<Class, Integer[]> TYPE_EMPLOYEES = Map.of(Hourly.class, new Integer[]{0, 10},
            Salaried.class, new Integer[]{1, 8},
            Commisioned.class, new Integer[]{2, 9});


    public static final ArrayList<PaymentBills> DEFAULT_TYPE_PAYMENTS = new ArrayList<>();

    static {
        try {
            DEFAULT_TYPE_PAYMENTS.add(new PaymentBills(6, 0, 0));
            DEFAULT_TYPE_PAYMENTS.add(new PaymentBills(-1, 0, 0));
            DEFAULT_TYPE_PAYMENTS.add(new PaymentBills(6, 1, 0));
        } catch (CloneNotSupportedException | InvalidAttributesException e) {
            e.printStackTrace();
        }
    }

}
