package funcionabilities;

import funcionabilities.auxiliary_entities.ISyndicates;
import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.calendar.IPointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

import java.util.HashMap;
import java.util.Map;

public class Employee implements Cloneable{
    private boolean Name;
    private String adress;
    private String name;
    private int personal_id;
    private ISyndicates personalSyndicate;
    private IMethodsPayments methodPayment;
    private ITypePayments personalIPayment;
    IPointCalendar worker;
    private Map<String, Double> debit;


    Employee(String adress, String name, int personal_id, ISyndicates personalSyndicate,
             IMethodsPayments methodPayment, ITypePayments personalIPayment, IPointCalendar worker) {
        this.adress = adress;
        this.name = name;
        this.personal_id = personal_id;
        this.personalSyndicate = personalSyndicate;
        this.methodPayment = methodPayment;
        this.personalIPayment = personalIPayment;
        this.worker = worker;
        this.debit = new HashMap<>();
    }

    public void addDebit(String service, double value) {
        debit.put(service, value);
    }

    public void removeDebit(String service) {
        debit.remove(service);
    }

    public void removeDebit(String service, double value) {
        debit.remove(service, value);
    }

    public boolean changeEmployeesDetails() {
        return false;
    }

    public boolean checkItsDay() {
        return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Information of employee:" + "\n\tName: ").append(name).append("\n\tAdress:  ")
                .append(adress).append("\n\tId: ").append(personal_id).append("\n\n\tSyndicate: ");
        if(personalSyndicate != null)  str.append(personalSyndicate.toString());
        else str.append("None");

        str.append("\n\n\tMethod of payment: ").append(methodPayment.toString()).
                append("\n\n\tAgend of payment: ").append(personalIPayment.toString()).
                append("\n\n\tPoint of employee: ").append(worker.toString());

        return str.toString();
    }

    public Employee clone() throws CloneNotSupportedException{
        Employee item = (Employee) super.clone();
        item.personalSyndicate = this.personalSyndicate.clone();
        item.methodPayment = this.methodPayment.clone();
        item.personalIPayment = this.personalIPayment.clone();
        item.worker = this.worker.clone();

        return item;
    }

    public String getName() {
        return name;
    }

    public IPointCalendar getWorker() {
        return  worker;
    }
}