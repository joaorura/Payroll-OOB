package funcionabilities;

import funcionabilities.auxiliary_entities.ISyndicates;
import funcionabilities.functional_aids.calendar.IPointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

public class Employee implements Cloneable{
    private String adress;
    private String name;
    private long personal_id;
    private ISyndicates personalSyndicate;
    private IMethodsPayments typePayment;
    private ITypePayments personalIPayment;
    IPointCalendar worker;

    Employee(String adress, String name, long personal_id, ISyndicates personalSyndicate,
             IMethodsPayments typePayment, ITypePayments personalIPayment, IPointCalendar worker) {
        this.adress = adress;
        this.name = name;
        this.personal_id = personal_id;
        this.personalSyndicate = personalSyndicate;
        this.typePayment = typePayment;
        this.personalIPayment = personalIPayment;
        this.worker = worker;
    }

    public boolean changeEmployeesDetails() {
        return false;
    }

    public boolean checkItsDay() {
        return false;
    }

    public String toString() {
        return "Information of employee:"
                + "\n\tName: " + name
                + "\n\tAdress " + adress
                + "\n\tId: " + personal_id
                + "\n\tSyndicate: " + personalSyndicate.toString()
                + "\n\tMethod of payment: " + typePayment.toString()
                + "\t\tAgend of payment: " +  personalIPayment.toString()
                + "\n\tPoint of employee: " + worker.toString();
    }

    public Employee clone() throws CloneNotSupportedException{
        Employee item = (Employee) super.clone();
        item.personalSyndicate = this.personalSyndicate.clone();
        item.typePayment = this.typePayment.clone();
        item.personalIPayment = this.personalIPayment.clone();
        item.worker = this.worker.clone();

        return item;
    }
}
