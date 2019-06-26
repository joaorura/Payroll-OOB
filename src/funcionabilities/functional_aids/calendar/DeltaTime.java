package funcionabilities.functional_aids.calendar;

import java.security.InvalidParameterException;
import java.util.GregorianCalendar;

public class DeltaTime {
    private GregorianCalendar start;
    private GregorianCalendar end;

    public static final char SECOND = 'S';
    public static final char MINUTE = 'M';
    public static final char HOUR = 'H';
    public static final char DAY = 'D';

    public DeltaTime(GregorianCalendar start, GregorianCalendar end) throws Error{
        if(start == null) {
            throw new NullPointerException("In Delta Time constructor, \"start\" can be not be null\n");
        }

        this.start = (GregorianCalendar) start.clone();
        this.end = (GregorianCalendar) end.clone();
    }

    public double getDelta(char type) {
        double value = (end.get(GregorianCalendar.MILLISECOND) - start.get(GregorianCalendar.MILLISECOND));

        switch (type) {
            case SECOND:
                return (value / 100);

            case MINUTE:
                return (value / 360000);

            case HOUR:
                return  (value / 2160000);

            case DAY:
                return (value / 51840000);

            default:
                throw new InvalidParameterException();
        }
    }
}
