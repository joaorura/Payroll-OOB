package funcionabilities.functional_aids.transactions;

public class CheckHands extends Check implements IMethodsPayments {
    private final int id_to_send;

    public CheckHands(BankAcount bank, double value, String name, int ids) {
        super(bank , value, name);
        this.id_to_send = ids;
    }

    public String getInfo() {
        String[] str = super.getInfo().split("\n\t");
        StringBuilder new_str = new StringBuilder();
        new_str.append(str[0]).append("\n\tin Hands to: \n\t\tId: ")
                .append(id_to_send).append("\n");

        for (int i = 1; i < str.length; i++) new_str.append(str[i]).append("\n\t");
        return new_str.toString();
    }

    public double doPayment() {
        getInfo();
        return super.value;
    }

    public IMethodsPayments clone() throws CloneNotSupportedException{
        return (CheckHands) super.clone();
    }
}
