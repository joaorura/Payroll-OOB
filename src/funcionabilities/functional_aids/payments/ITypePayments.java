package funcionabilities.functional_aids.payments;

import funcionabilities.functional_aids.calendar.Calendar;

public interface ITypePayments {
    ITypePayments clone() throws CloneNotSupportedException;

    void setLastPayment(Object item);

    String toString();

    boolean checkItsDay(Calendar calendar);
}
