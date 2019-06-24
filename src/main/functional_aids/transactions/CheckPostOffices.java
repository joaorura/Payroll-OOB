package main.functional_aids.transactions;

public class CheckPostOffices extends Check implements IMethodsPayments{
    private String adress;

    public CheckPostOffices(String source, double value, String name, String adress) {
        super(source, value, name);
        this.adress = adress;
    }

    public String getInfo() {
        String[] str = super.getInfo().split("\n\t");
        StringBuffer new_str = new StringBuffer();

        new_str.append(str[0]);
        new_str.append("\n\tthrough the post office, to: \n\t\tAdress: ");
        new_str.append(adress).append("\n");
        for(int i = 1; i < str.length; i ++) new_str.append(str[i]).append("\n\t");
        return  new_str.toString();
    }

    public double doPayment() {
        System.out.println(getInfo());
        return super.value;
    }

    public IMethodsPayments clone() {
        return null;
    }
}
