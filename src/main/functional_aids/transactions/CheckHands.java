package main.functional_aids.transactions;

public class CheckHands extends Check implements IMethodsPayments{
    private int id_to_send;
    private String order;

    public CheckHands(String source, double value, String name, int ids, String order) {
        super(source, value, name);
        this.id_to_send = ids;
        this.order = order;
    }

    public String getInfo() {
        String[] str = super.getInfo().split("\n\t");
        String new_str = str[0] + "\n\tin Hands to: \n\t\tId: " + id_to_send + "\n\t\tOrder:" + order + "\n";

        for(int i = 1; i < str.length; i ++) new_str += str[i] + "\n\t";
        return  new_str;
    }

    public double doPayment() {
        getInfo();
        return super.value;
    }

    public IMethodsPayments clone() {
        return null;
    }
}
