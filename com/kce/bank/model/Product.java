package com.kce.bank.model;

public class Product {
 public String id;
 public String name;
 public int stock;

 public Product(String id, String name, int stock) {
     this.id = id;
     this.name = name;
     this.stock = stock;
 }

 public void addStock(int qty) {
     stock += qty;
 }

 public boolean removeStock(int qty) {
     if (stock >= qty) {
         stock -= qty;
         return true;
     }
     else {
         System.out.println("Not enough stock available"+ name);
         return false;
     }
 }
}




