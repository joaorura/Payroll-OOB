package funcionabilities.functional_aids.transactions;

public class Deposit extends BankTransaction implements IMethodsPayments {
    private final String acountSend;

    public Deposit(BankAcount bank, double value, String send) {
        super(bank, value);
        this.acountSend = send;
    }

    public String getInfo() {
        String[] str = super.getInfo().split("\n\t");
        StringBuilder new_str = new StringBuilder();
        new_str.append(str[0])
                .append("Depoisit\n\tFor acount: ")
                .append(acountSend).append("\n\t");

        for (int i = 1; i < str.length; i++) new_str.append(str[i]).append("\n\t");
        return new_str.toString();
    }

    @Override
    public double doPayment() {
        System.out.println(getInfo());
        return super.value;
    }

    @Override
    public IMethodsPayments clone() throws CloneNotSupportedException {
        return (Deposit) super.clone();
    }
}
