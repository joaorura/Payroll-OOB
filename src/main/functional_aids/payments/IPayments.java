package main.functional_aids.payments;

import java.util.GregorianCalendar;

public interface IPayments {
    public IPayments clone();
    public boolean checkItsDay(GregorianCalendar calendar);
}
