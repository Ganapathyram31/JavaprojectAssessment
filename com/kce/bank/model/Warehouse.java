package com.kce.bank.model;

import java.util.ArrayList;


public class Warehouse {
    public String name;
    public ArrayList<Product> products = new ArrayList<>();

    public Warehouse(String name) {
        this.name = name;
    }
    public void addProduct(Product p) {
        products.add(p);
    }
    public void showStock() {
        System.out.println("---- Stock in " + name + " ----");
        for (Product p : products) {
            System.out.println(p.id + " - " + p.name + " : " + p.stock);
        }
    }
}
