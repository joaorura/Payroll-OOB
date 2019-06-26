package funcionabilities.functional_aids.payments;

import java.util.GregorianCalendar;

public interface ITypePayments {
    ITypePayments clone() throws CloneNotSupportedException;

    void setLastPayment(Object item);

    boolean checkItsDay(GregorianCalendar calendar);
}
