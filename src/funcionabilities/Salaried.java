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

    @Override
    public double attMoney() {
        int days = super.worker.workedDays();
        double tempSalary = salary / 30;
        tempSalary *= days;
        tempSalary -= super.debts.getValueDebt();
        tempSalary -= days * (super.getSyndicate().getMonthlyFee() / 30);
        tempSalary = super.getDebts().calculate(tempSalary);

        super.getMethodPayment().setValue(tempSalary);
        return tempSalary;
    }
}
