package funcionabilities.functional_aids.transactions;

public class CheckHands extends Check implements IMethodsPayments {
    private final int id_to_send;

    public CheckHands(BankAcount bank, double value, String name, int ids) {
        super(bank , value, name);
        this.id_to_send = ids;
    }

    public double doPayment() {
        getInfo();
        return super.value;
    }

    public IMethodsPayments clone() throws CloneNotSupportedException{
        return (CheckHands) super.clone();
    }

    public String toString() {
        return super.toString() + "\nId to employeer: " + id_to_send + "\n\t";
    }
}
