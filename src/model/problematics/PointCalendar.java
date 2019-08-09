package model.problematics;

import java.util.ArrayList;

public class PointCalendar implements Cloneable {
    private ArrayList<Double> pcalendar = new ArrayList<>();

    private final String identifier = "\n\tThis employeer work: " + amountWork(-1) + "hours" + "\n";

    int workedDays() {
        try {
            return pcalendar.size();
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    double amountWork(int day) {
        if (day == -1) {
            double value = 0;
            for (int i = 0; i < pcalendar.size(); i++) {
                value += amountWork(i);
            }

            return value;
        } else {
            return pcalendar.get(day);
        }
    }

    public String toString() {
        return identifier;
    }

    public void markPoint(Calendar start, Calendar end) {
        pcalendar.add(Calendar.getDeltaMinutes(start, end) / 60);
    }

    public PointCalendar clone() throws CloneNotSupportedException {
        PointCalendar new_item = (PointCalendar) super.clone();
        //noinspection unchecked
        new_item.pcalendar = (ArrayList<Double>) this.pcalendar.clone();

        return new_item;
    }
}
