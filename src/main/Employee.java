package main;

import main.auxiliary_entities.ISyndicates;
import main.functional_aids.calendar.IPointCalendar;
import main.functional_aids.payments.IPayments;
import main.functional_aids.transactions.IMethodsPayments;

public class Employee {
    protected String adress;
    protected String name;
    protected long personal_id;
    protected ISyndicates personalSyndicate;
    protected IMethodsPayments typePayment;
    protected IPayments personalIPayment;
    protected IPointCalendar worker;

    public Employee(String adress, String name, long personal_id, ISyndicates personalSyndicate,
                    IMethodsPayments typePayment, IPayments personalIPayment, IPointCalendar worker) {
        this.adress = adress;
        this.name = name;
        this.personal_id = personal_id;
        this.personalSyndicate = personalSyndicate;
        this.typePayment = typePayment;
        this.personalIPayment = personalIPayment;
        this. worker = worker;
    }

    public Employee clone() {
        Employee new_object= new Employee(adress, name, personal_id, personalSyndicate.clone(), typePayment.clone(),
                personalIPayment.clone(), worker.clone());

        return new_object;
    }

    public boolean changeEmployeesDetails() {
        return false;
    }

    public boolean checkItsDay() {
        return false;
    }
}
