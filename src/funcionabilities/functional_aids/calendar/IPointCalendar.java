package funcionabilities.functional_aids.calendar;

import java.util.GregorianCalendar;

public interface IPointCalendar {
    IPointCalendar clone() throws CloneNotSupportedException;

    String toString();

    double amountWork(int day);

    int countedDays();

    void markPoint(GregorianCalendar start, GregorianCalendar end);
}
