package funcionabilities.functional_aids.transactions;

public class Check extends BankTransaction {
    private String name;

    public Check(String source, double value, String name) {
        super(source, value);
        this.name = name;
    }

    public String getInfo() {
        String[] str = super.getInfo().split("\n\t");
        StringBuilder new_str = new StringBuilder();
        new_str.append(str[0])
                .append("Check\n\tFor name: ")
                .append(name).append("\n\t");

        for (int i = 1; i < str.length; i++) new_str.append(str[i]).append("\n\t");
        return new_str.toString();
    }

}
