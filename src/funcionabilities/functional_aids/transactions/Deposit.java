package funcionabilities.functional_aids.transactions;

public class Deposit extends BankTransaction implements IMethodsPayments {
    private final String acountSend;

    public Deposit(BankAcount bank, double value, String send) {
        super(bank, value);
        this.acountSend = send;
    }

    @Override
    public String toString() {
        return super.toString() + "Acount destiny: " + acountSend;
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
