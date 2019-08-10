package model.problematics;

import java.util.ArrayList;

public class PointCalendar implements Cloneable {
    private ArrayList<Double> paymentCalendar = new ArrayList<>();

    private final String identifier = "\n\tThis employee work: " + amountWork(-1) + "hours" + "\n";

    int workedDays() {
        try {
            return paymentCalendar.size();
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    double amountWork(int day) {
        if (day == -1) {
            double value = 0;
            for (int i = 0; i < paymentCalendar.size(); i++) {
                value += amountWork(i);
            }

            return value;
        } else {
            return paymentCalendar.get(day);
        }
    }

    public String toString() {
        return identifier;
    }

    public void markPoint(Calendar start, Calendar end) {
        paymentCalendar.add(Calendar.getDeltaMinutes(start, end) / 60);
    }

    public PointCalendar clone() throws CloneNotSupportedException {
        PointCalendar new_item = (PointCalendar) super.clone();
        //noinspection unchecked
        new_item.paymentCalendar = (ArrayList<Double>) this.paymentCalendar.clone();

        return new_item;
    }
}
