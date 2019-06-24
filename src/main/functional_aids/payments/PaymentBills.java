package main.functional_aids.payments;

import java.util.GregorianCalendar;

public class PaymentBills implements IPayments {
    private int day;
    private int weekInterval;
    private int monthInterval;
    private GregorianCalendar lastPayment;
    private GregorianCalendar nextDayPayment;

    public PaymentBills(GregorianCalendar lastPayment, int day, int weekInterval, int monthInterval) {
        this.day = day;
        this.weekInterval = weekInterval;
        this.monthInterval = monthInterval;
        this.lastPayment = (GregorianCalendar) lastPayment.clone();

        att();
    }

    public PaymentBills(GregorianCalendar lastPayment, int day, int interval) {
        this.day = day;

        if (day == -1) {
            this.weekInterval = 0;
            this.monthInterval = interval;
        } else {
            this.weekInterval = interval;
            this.monthInterval = 0;
        }

        this.lastPayment = (GregorianCalendar) lastPayment.clone();
    }

    private void att() {
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

    @Override
    public IPayments clone() {
        IPayments item;
        try {
            item = (IPayments) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }

        return item;
    }

    @Override
    public boolean checkItsDay(GregorianCalendar calendar) {
        if (calendar.equals(nextDayPayment)) {
            att();
            return true;
        } else {
            return false;
        }
    }
}
