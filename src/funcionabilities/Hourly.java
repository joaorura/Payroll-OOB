package funcionabilities;

import funcionabilities.auxiliary_entities.ISyndicates;
import funcionabilities.functional_aids.calendar.IPointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

import java.util.GregorianCalendar;

public class Hourly extends Employee{
    private final int max_work_hours;
    private final double tax_over_work;
    private final double ratioHour;

    public Hourly(String adress, String name, int personal_id, ISyndicates personalSyndicate,
                  IMethodsPayments typePayment, ITypePayments personalIPayment, IPointCalendar worker, int max_work_hours,
                  double tax_over_work, double ratioHour) {
        super(adress, name, personal_id, personalSyndicate, typePayment, personalIPayment, worker);

        this.max_work_hours = max_work_hours;
        this.tax_over_work = tax_over_work;
        this.ratioHour = ratioHour;
    }

    public double launchDotCard(GregorianCalendar start, GregorianCalendar end) {
        super.worker.markPoint(start, end);
        return  super.worker.amountWork(-1);
    }

    public int getMaxWorkHours() {
        return max_work_hours;
    }

    public double getOverWork() {
        return tax_over_work;
    }

    public double geRatioWork() {
        return ratioHour;
    }
}
