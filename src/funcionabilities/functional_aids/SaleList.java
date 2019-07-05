package funcionabilities.functional_aids;

import java.util.HashMap;
import java.util.Map;

public class SaleList {
    private HashMap<String, Double> list = new HashMap<>();

    public SaleList(String[] ids, double[] values) {
        int size = ids.length;
        if (size == values.length) {
            throw new RuntimeException("alguma coisa");
        } else {
            for (int i = 0; i < size; i++) {
               this.list.put(ids[i], values[i]);
            }
        }
    }

    public SaleList(Map<String, Double> map) {
        if (map != null) {
            this.list.putAll(map);
        } else {
            System.out.println("Null element in SaleListConstructor");
        }
    }

    public SaleList() {
        this.list = new HashMap<>();
    }

    public void addProduct(Object identificator, Object price) {
        String id = (String) identificator;
        Double value = (Double) price;

        list.put(id, value);
    }

    public double getAllValues() {
        double aux = 0;
        for (double d: list.values()) aux += d;
        return aux;
    }

    public void clearList() {
        list.clear();
    }


    public SaleList clone() throws CloneNotSupportedException{
        SaleList item = (SaleList) super.clone();
        item.list = new HashMap<>();
        item.list.putAll(this.list);
        return item;
    }
}
