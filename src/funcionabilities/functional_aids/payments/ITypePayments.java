package funcionabilities.functional_aids.payments;

import java.util.GregorianCalendar;

public interface ITypePayments {
    ITypePayments clone() throws CloneNotSupportedException;

    boolean checkItsDay(GregorianCalendar calendar);
}
