package main.functional_aids.transactions;

public class Deposit extends BankTransaction implements IMethodsPayments{
    private String acountSend;

    public Deposit(String source, Double value, String send) {
        super(source, value);
    }

    public String getInfo() {
        String[] str = super.getInfo().split("\n\t");
        String new_str = str[0] + "Depoisit\n\tFor acount: " + acountSend + "\n\t";

        for(int i = 1; i < str.length; i ++) new_str += str[i];
        return  new_str;
    }

    @Override
    public double doPayment() {
        System.out.println(getInfo());
        return super.value;
    }

    @Override
    public IMethodsPayments clone() {
        return null;
    }
}
