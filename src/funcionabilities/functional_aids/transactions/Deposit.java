package funcionabilities.functional_aids.transactions;

public class Deposit extends BankTransaction implements IMethodsPayments {
    private final String acountSend;

    private String identifier;

    public Deposit(BankAccount bank, double value, String send) {
        super(bank, value);
        this.acountSend = send;
        this.identifier = super.toString() + "Acount destiny: " + acountSend;
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public IMethodsPayments clone() throws CloneNotSupportedException {
        return (Deposit) super.clone();
    }

    @Override
    public void setValue(double value) {
        super.value = value;
    }
}
