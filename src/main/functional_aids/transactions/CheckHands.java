package main.functional_aids.transactions;

public class CheckHands extends Check implements IMethodsPayments {
    private int id_to_send;
    private String order;

    public CheckHands(String source, double value, String name, int ids, String order) {
        super(source, value, name);
        this.id_to_send = ids;
        this.order = order;
    }

    public String getInfo() {
        String[] str = super.getInfo().split("\n\t");
        StringBuilder new_str = new StringBuilder();
        new_str.append(str[0]).append("\n\tin Hands to: \n\t\tId: ")
                .append(id_to_send).append("\n\t\tOrder:")
                .append(order).append("\n");

        for (int i = 1; i < str.length; i++) new_str.append(str[i]).append("\n\t");
        return new_str.toString();
    }

    public double doPayment() {
        getInfo();
        return super.value;
    }

    public IMethodsPayments clone() {
        return null;
    }
}
