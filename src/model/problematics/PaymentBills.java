package model.problematics;


import javax.naming.directory.InvalidAttributesException;

public class PaymentBills implements Cloneable {
    private int day;
    private int weekInterval;
    private int monthInterval;

    private Calendar lastPayment;
    private Calendar nextDayPayment;

    private String identifier;

    public PaymentBills(Calendar lastPayment, int day, int weekInterval, int monthInterval)
            throws InvalidAttributesException, CloneNotSupportedException {
        if ((day == -1 || day > 0) && weekInterval >= 0 && monthInterval >= 0) {
            this.day = day;
            this.weekInterval = weekInterval;
            this.monthInterval = monthInterval;

            if (lastPayment != null) {
                try {
                    this.lastPayment = lastPayment.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                att();
            } else {
                this.lastPayment = null;
            }
        } else {
            throw new Error("All parameters must be positive.");
        }

        this.identifier = constructString();
    }

    public PaymentBills(int day, int weekInterval, int monthInterval)
            throws CloneNotSupportedException, InvalidAttributesException {
        this(null, day, weekInterval, monthInterval);
    }

    private String constructString() {
        return "\tDay of week: " + day + "  |  Week Interval: " + weekInterval +
                "  |  Month Interval: " + monthInterval + "\n";
    }

    private void checkLastPayment() {
        if (lastPayment == null) {
            throw new Error("The last payments cant be a null please call setLastPayment()");
        }
    }

    private void att() throws CloneNotSupportedException, InvalidAttributesException {
        checkLastPayment();

        if (nextDayPayment != null)
            this.lastPayment = nextDayPayment;

        this.nextDayPayment = lastPayment.clone();
        nextDayPayment.add(Calendar.DAY_OF_MONTH, weekInterval * 7);

        if (day == -1) {
            nextDayPayment.add(Calendar.DAY_OF_MONTH,
                    nextDayPayment.getMaximumMonthDays() % nextDayPayment.get(Calendar.DAY_OF_MONTH));

            int day_of_week = nextDayPayment.get(Calendar.DAY_OF_WEEK);
            if (day_of_week == 1) {
                nextDayPayment.add(Calendar.DAY_OF_MONTH, 1);
            } else if (day_of_week == 7) {
                nextDayPayment.add(Calendar.DAY_OF_MONTH, 2);
            }
        } else {
            if (day == nextDayPayment.get(Calendar.DAY_OF_WEEK) && weekInterval == 0 && monthInterval == 0) {
                nextDayPayment.add(Calendar.DAY_OF_MONTH, 1);
            }

            int aux = (day - nextDayPayment.get(Calendar.DAY_OF_WEEK));
            if (aux < 0) aux += 7;

            nextDayPayment.add(Calendar.DAY_OF_MONTH, aux);
        }
    }

    public void setLastPayment(Object item) throws InvalidAttributesException, CloneNotSupportedException {
        assert item instanceof Calendar;
        this.lastPayment = (Calendar) item;
        att();
    }

    public boolean checkItsDay(Calendar calendar) {
        checkLastPayment();

        if (calendar.equals(nextDayPayment)) {
            try {
                att();
            } catch (CloneNotSupportedException | InvalidAttributesException e) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public PaymentBills clone() throws CloneNotSupportedException {
        PaymentBills type = (PaymentBills) super.clone();

        if (lastPayment != null)
            type.lastPayment = lastPayment.clone();
        else
            type.lastPayment = null;

        if (nextDayPayment != null)
            type.nextDayPayment = nextDayPayment.clone();
        else
            type.nextDayPayment = null;

        return type;
    }
}
