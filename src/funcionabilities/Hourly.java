package funcionabilities;

import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.calendar.PointCalendar;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

public class Hourly extends Employee {
    private final int max_work_hours;
    private final double tax_over_work;
    private final double ratioHour;

    public Hourly(String adress, String name, int personal_id, Syndicate personalSyndicate,
                  IMethodsPayments typePayment, PaymentBills personalIPayment, PointCalendar worker, int max_work_hours,
                  double tax_over_work, double ratioHour) {
        super(adress, name, personal_id, personalSyndicate, typePayment, personalIPayment, worker);

        this.max_work_hours = max_work_hours;
        this.tax_over_work = tax_over_work;
        this.ratioHour = ratioHour;
    }

    public int getMaxWorkHours() {
        return max_work_hours;
    }

    public double getTaxOverWork() {
        return tax_over_work;
    }

    public double getRatioWork() {
        return ratioHour;
    }

    @Override
    public double attMoney() {
        double time = super.getWorker().amountWork(-1);
        double tempSalary = ratioHour * (time % max_work_hours);
        time -= time % max_work_hours;
        tempSalary += tax_over_work * time;
        time = super.getWorker().amountWork(-1);
        if (super.getSyndicate() != null) tempSalary -= time * (super.getSyndicate().getMonthlyFee() / (time / 24));
        tempSalary = super.getDebts().calculate(tempSalary);

        super.getMethodPayment().setValue(tempSalary);
        return tempSalary;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tMax over hours: " + max_work_hours + "\n" +
                "Tax over work: " + tax_over_work + "\n" +
                "Ratio of hour: " + ratioHour + "\n";
    }

    public Hourly clone() throws CloneNotSupportedException {
        return (Hourly) super.clone();
    }
}
