package funcionabilities;

import funcionabilities.auxiliary_entities.ISyndicates;
import funcionabilities.functional_aids.calendar.IPointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

public class Salaried extends Employee {
    private final Double salary;

    public Salaried(String adress, String name, int personal_id, ISyndicates personalSyndicate,
                    IMethodsPayments typePayment, ITypePayments personalIPayment, IPointCalendar worker, Double salary) {
        super(adress, name, personal_id, personalSyndicate, typePayment, personalIPayment, worker);
        this.salary = salary;
    }

    public double attMoney() {
        int checker = 0, i;
        for(i = 0; i < super.worker.countedDays(); i++) {
            if(super.worker.amountWork(i) != 0) checker ++;
        }

        double temp_salary = (checker * salary) / worker.countedDays();
        if(super.getSyndicate() != null)
            temp_salary -= checker * (super.getSyndicate().costSyndicate() / 30);

        temp_salary -= checker * (super.getDebitCost() / 30);

        super.clearDebit();

        return temp_salary;
    }
}
