package funcionabilities.functional_aids.payments;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PaymentBills implements ITypePayments {
    private int day;
    private int weekInterval;
    private int monthInterval;
    private GregorianCalendar lastPayment;
    private GregorianCalendar nextDayPayment;

    public PaymentBills(GregorianCalendar lastPayment, int day, int weekInterval, int monthInterval) {
        if((day == -1 || day > 0) && weekInterval >= 0 && monthInterval >= 0) {
            this.day = day;
            this.weekInterval = weekInterval;
            this.monthInterval = monthInterval;

            if(lastPayment != null) {
                this.lastPayment = (GregorianCalendar) lastPayment.clone();
                att();
            }
            else {
                this.lastPayment = null;
            }
        }
        else {
            throw new Error("Todos os parametros devem ser positivos");
        }
    }

    public PaymentBills(int day, int weekInterval, int monthInterval) {
        this(null, day, weekInterval, monthInterval);
    }

    public PaymentBills(GregorianCalendar lastPayment, int day, int monthInterval) {
        this(lastPayment, day, 0, monthInterval);
    }

    private void checkLastPaymet() {
        if(lastPayment == null) {
            throw new Error("The last payments cant be a null please call setLastPayment()");
        }
    }
    private void att() {
        checkLastPaymet();

        this.lastPayment = nextDayPayment;
        this.nextDayPayment = (GregorianCalendar) lastPayment.clone();

        nextDayPayment.add(GregorianCalendar.MONTH, monthInterval);
        nextDayPayment.add(GregorianCalendar.WEEK_OF_YEAR, weekInterval);

        if (day == -1) {
            nextDayPayment.set(GregorianCalendar.DAY_OF_MONTH,
                    nextDayPayment.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
            int day_of_week = nextDayPayment.get(GregorianCalendar.DAY_OF_WEEK);
            if (day_of_week == 1) {
                nextDayPayment.add(GregorianCalendar.DAY_OF_MONTH, 1);
            } else if (day_of_week == 7) {
                nextDayPayment.add(GregorianCalendar.DAY_OF_MONTH, 2);
            }
        } else {
            nextDayPayment.set(GregorianCalendar.DAY_OF_WEEK, day);
        }
    }

    public String toString() {
        return "\tDay of week: " + day + "  |  Week Interval: " + weekInterval +
                "  |  Month Interval: " + monthInterval + "\n";
    }

    @Override
    public ITypePayments clone() {
        PaymentBills type = new PaymentBills(lastPayment, day, weekInterval, monthInterval);
        if(type.nextDayPayment != null)
            type.nextDayPayment = (GregorianCalendar) this.nextDayPayment.clone();

        return type;
    }

    @Override
    public void setLastPayment(Object item) {
        assert item instanceof Calendar;
        this.lastPayment = (GregorianCalendar) item;
    }

    @Override
    public boolean checkItsDay(GregorianCalendar calendar) {
        checkLastPaymet();

        if (calendar.equals(nextDayPayment)) {
            att();
            return true;
        } else {
            return false;
        }
    }
}
