package funcionabilities.functional_aids.transactions;

public class Deposit extends BankTransaction implements IMethodsPayments {
    private String acountSend;

    public Deposit(String source, Double value, String send) {
        super(source, value);
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
