package model;

import java.util.Hashtable;

public class SaleList implements Cloneable {
    private Hashtable<String, Double> list;

    SaleList() {
        this.list = new Hashtable<>();
    }

    public void addProduct(String identifier, Double price) {
        list.put(identifier, price);
    }

    double getAllValues() {
        double aux = 0;
        for (double d : list.values()) aux += d;
        return aux;
    }

    void clearList() {
        list.clear();
    }

    public SaleList clone() throws CloneNotSupportedException {
        SaleList item = (SaleList) super.clone();
        item.list = new Hashtable<>();
        item.list.putAll(this.list);
        return item;
    }
}
