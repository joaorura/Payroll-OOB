package funcionabilities;

import funcionabilities.auxiliary_entities.ISyndicates;
import funcionabilities.functional_aids.calendar.IPointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

public class Salaried extends Employee {
    private  Double salary;

    public Salaried(String adress, String name, int personal_id, ISyndicates personalSyndicate,
                    IMethodsPayments typePayment, ITypePayments personalIPayment, IPointCalendar worker, Double salary) {
        super(adress, name, personal_id, personalSyndicate, typePayment, personalIPayment, worker);
        this.salary = salary;
    }
}
