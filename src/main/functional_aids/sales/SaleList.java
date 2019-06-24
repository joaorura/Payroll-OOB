package main.functional_aids.sales;

import java.util.HashMap;
import java.util.Map;

public class SaleList implements ISalesList{
    private HashMap<String, Double> list;


    public SaleList(String[] ids, double[] values) {
        int size = ids.length;
        if(size == values.length) {
            throw new RuntimeException("alguma coisa");
        }
        else {
            list = new HashMap<String, Double>();
            for(int i = 0; i < size; i++) {
                list.put(ids[i], values[i]);
            }
        }
    }

    public SaleList(Map<String, Double> map) {
            if (map != null) {
                 list.putAll(map);
            }
            else {
                System.out.println("Null element in SaleListConstructor");
            }
    }

    public void addProduct(Object identificator, Object price) {
        String id = (String) identificator;
        Double value = (Double) price;

        list.put(id, value);
    }

    public Double removeProduct(Object identificator) {
        String id = (String) identificator;
        return list.remove(id);
    }

    public HashMap getList() {
        HashMap<String, Double> new_item = new HashMap<String, Double>();
        new_item.putAll(list);

        return new_item;
    }

    public ISalesList clone() {
        return null;
    }
}
