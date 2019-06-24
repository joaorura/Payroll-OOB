package main.functional_aids.sales;

import java.util.Map;

public interface ISalesList<T,H> {
    public void addProduct(T identificator, H price);
    public H removeProduct(T identificator);
    public Map<T, H> getList();
    public ISalesList clone();
}
