package funcionabilities;

import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.calendar.PointCalendar;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

public class Salaried extends Employee {
    private final Double salary;

    public Salaried(String adress, String name, int personal_id, Syndicate personalSyndicate,
                    IMethodsPayments typePayment, PaymentBills personalIPayment, PointCalendar worker, Double salary) {
        super(adress, name, personal_id, personalSyndicate, typePayment, personalIPayment, worker);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
