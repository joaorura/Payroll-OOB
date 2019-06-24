package funcionabilities.functional_aids.calendar;

import java.util.GregorianCalendar;

public interface IPointCalendar {
    IPointCalendar clone() throws CloneNotSupportedException;

    double amountWork(int day);

    void markPoint(GregorianCalendar start, GregorianCalendar end);
}
