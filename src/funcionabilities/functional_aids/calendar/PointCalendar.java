package funcionabilities.functional_aids.calendar;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PointCalendar implements IPointCalendar {
    private ArrayList<Double> pcalendar = new ArrayList<>();
    private int type;

    private static final int HOURLY = 0;
    private static final int DAILY = 1;
    private static final int MONTHLY = 2;

    public PointCalendar(int type) {
        if(type != HOURLY && type != DAILY & type != MONTHLY) {
            throw new RuntimeException("Type in PointCalendar Constructor, it's wrong, this is can be 'H' or 'D'\n");
        }

        this.type = type;
    }


    public IPointCalendar clone() throws CloneNotSupportedException {
        PointCalendar new_item = (PointCalendar) super.clone();
        new_item.pcalendar = new ArrayList<>();
        new_item.pcalendar.addAll(this.pcalendar);

        return new_item;
    }

    public double amountWork(int day) {
        if(day == -1) {
            double value = 0;
            for(int i = 0; i < pcalendar.size(); i ++) {
                value += amountWork(i);
            }

            return value;
        }
        else {
            return pcalendar.get(day);
        }
    }

    public String toString() {
      return  "\n\tThis employeer work: " + amountWork(-1) + "hours" + "\n";
    }
    public void markPoint(Calendar start, Calendar end) {
        pcalendar.add(Calendar.getDeltaMinutes(start, end) / 24);
    }
}
