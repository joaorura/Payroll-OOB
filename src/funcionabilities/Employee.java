package funcionabilities;

import funcionabilities.auxiliary_entities.Syndicate;
import funcionabilities.functional_aids.calendar.PointCalendar;
import funcionabilities.functional_aids.PaymentBills;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

import java.util.HashMap;
import java.util.Map;

public abstract class Employee implements Cloneable{
    private String adress;
    private String name;
    private int personal_id;
    private Syndicate personalSyndicate;
    private IMethodsPayments methodPayment;
    private PaymentBills personalIPayment;
    PointCalendar worker;



    Employee(String adress, String name, int personal_id, Syndicate personalSyndicate,
             IMethodsPayments methodPayment, PaymentBills personalIPayment, PointCalendar worker) {
        this.adress = adress;
        this.name = name;
        this.personal_id = personal_id;
        this.personalSyndicate = personalSyndicate;
        this.methodPayment = methodPayment;
        this.personalIPayment = personalIPayment;
        this.worker = worker;
    }

    public abstract void attMoney();



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
        item.personalSyndicate =  this.personalSyndicate.clone();
        item.methodPayment = this.methodPayment.clone();
        item.personalIPayment = this.personalIPayment.clone();
        item.worker = this.worker.clone();

        return item;
    }

    public void setId(int id) {
        this.personal_id = id;
    }

    public void setPersonalIPayment(PaymentBills personalIPayment) {
        this.personalIPayment = personalIPayment;
    }

    public PaymentBills getPersonalIPayment() {
        return personalIPayment;
    }

    public IMethodsPayments getMethodPayment() {
        return methodPayment;
    }

    public String getName() {
        return name;
    }

    public PointCalendar getWorker() {
        return  worker;
    }

    public String getAdress() {
        return adress;
    }

    public Syndicate getSyndicate() {
        return personalSyndicate;
    }
}
