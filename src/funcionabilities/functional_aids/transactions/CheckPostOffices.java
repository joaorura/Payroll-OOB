package funcionabilities.functional_aids.transactions;

public class CheckPostOffices extends Check implements IMethodsPayments {
    private final String adress;

    public CheckPostOffices(BankAcount bank,  double value, String name, String adress) {
        super(bank, value, name);
        this.adress = adress;
    }

    public String toString() {
        return super.toString() + "\n\tSend to adress: " + adress + "\n";
    }

    public double doPayment() {
        System.out.println(getInfo());
        return super.value;
    }

    public IMethodsPayments clone() throws CloneNotSupportedException{
        return (CheckPostOffices) super.clone();
    }
}
