package com.kce.bank.model;
import java.util.ArrayList;


public class BOM {
    public Product finishedProduct;
    public ArrayList<BOMItem> items = new ArrayList<>();

    public BOM(Product finishedProduct) {
        this.finishedProduct = finishedProduct;
    }
    
    public void addItem(Product component, int quantity) {
        items.add(new BOMItem(component, quantity));
    }
}
