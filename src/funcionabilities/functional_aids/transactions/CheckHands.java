package funcionabilities.functional_aids.transactions;

@SuppressWarnings("CanBeFinal")
public class CheckHands extends Check implements IMethodsPayments {
    private int id_to_send;

    public CheckHands(BankAcount bank, double value, String name, int ids) {
        super(bank, value, name);
        this.id_to_send = ids;
    }

    public IMethodsPayments clone() throws CloneNotSupportedException {
        return (CheckHands) super.clone();
    }

    @Override
    public void setValue(double value) {
        super.value = value;
    }

    public String toString() {
        return super.toString() + "\nId to employeer: " + id_to_send + "\n\t";
    }
}
