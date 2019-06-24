package main.functional_aids.transactions;

public class Check extends BankTransaction{
    private String name;

    public Check(String source, double value, String name) {
        super(source, value);
        this.name = name;
    }

    public String getInfo() {
        String[] str = super.getInfo().split("\n\t");
        String new_str = str[0] + "Check\n\tFor name: " + name + "\n\t";

        for(int i = 1; i < str.length; i ++) new_str += str[i];
        return  new_str;
    }

}
