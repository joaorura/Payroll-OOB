package model.problematics;

@SuppressWarnings("CanBeFinal")
public abstract class Employee implements Cloneable {
    PointCalendar worker;
    Debts debts;
    private String adress;
    private String name;
    private int personal_id;
    private Syndicate personalSyndicate;
    private IMethodsPayments methodPayment;
    private PaymentBills personalIPayment;
    private String identifier;

    Employee(String adress, String name, int personal_id, Syndicate personalSyndicate,
             IMethodsPayments methodPayment, PaymentBills personalIPayment, PointCalendar worker) {
        this.adress = adress;
        this.name = name;
        this.personal_id = personal_id;
        this.personalSyndicate = personalSyndicate;
        this.methodPayment = methodPayment;
        this.personalIPayment = personalIPayment;
        this.worker = worker;
        this.debts = new Debts();

        identifier = constructString();
    }

    private String constructString() {
        StringBuilder str = new StringBuilder();
        str.append("Information of employee:" + "\n\tName: ").append(name).append("\n\tAdress:  ")
                .append(adress).append("\n\tId: ").append(personal_id).append("\n\n\tSyndicate: ");
        if (personalSyndicate != null) str.append(personalSyndicate.toString());
        else str.append("None");

        str.append("\n\n\tMethod of payment: ").append(methodPayment.toString()).
                append("\n\n\tAgend of payment: ").append(personalIPayment.toString()).
                append("\n\n\tPoint of employee: ").append(worker.toString()).
                append("\n\n\tDebts: ").append(debts.toString());

        return str.toString();
    }

    public PaymentBills getPersonalIPayment() {
        return personalIPayment;
    }

    public void setPersonalIPayment(PaymentBills personalIPayment) {
        this.personalIPayment = personalIPayment;
    }

    public IMethodsPayments getMethodPayment() {
        return methodPayment;
    }

    public String getName() {
        return name;
    }

    public PointCalendar getWorker() {
        return worker;
    }

    public Debts getDebts() {
        return debts;
    }

    public Syndicate getSyndicate() {
        return personalSyndicate;
    }

    public int getId() {
        return personal_id;
    }

    public abstract double attMoney();

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        Employee item = (Employee) super.clone();

        if (personalSyndicate != null) item.personalSyndicate = this.personalSyndicate.clone();
        else item.personalSyndicate = null;

        item.methodPayment = this.methodPayment.clone();
        item.personalIPayment = this.personalIPayment.clone();
        item.worker = this.worker.clone();
        item.debts = this.debts.clone();

        return item;
    }
}
