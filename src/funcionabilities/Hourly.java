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

    public double attMoney() {
        double time = 0, aux, temp_salary = 0;
        for(int i = 0; i < worker.countedDays(); i ++) {
            aux = worker.amountWork(i);
            if(aux > tax_over_work) temp_salary += aux * (1 + ratioHour) * (1 + tax_over_work);
            else temp_salary += aux * (1 + ratioHour);
            time += aux;
        }

        temp_salary -= (time * getSyndicate().costSyndicate()) / 43200;
        temp_salary -= getDebitCost();
        clearDebit();

        return temp_salary;
    }
}
