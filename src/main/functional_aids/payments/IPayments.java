package main.functional_aids.payments;

import java.util.GregorianCalendar;

public interface IPayments {
    IPayments clone();

    boolean checkItsDay(GregorianCalendar calendar);
}
