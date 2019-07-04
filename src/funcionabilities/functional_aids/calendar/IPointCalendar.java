package funcionabilities.functional_aids.calendar;


public interface IPointCalendar {
    IPointCalendar clone() throws CloneNotSupportedException;

    String toString();

    double amountWork(int day);

    void markPoint(Calendar start, Calendar end);
}
