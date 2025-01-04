package com.example.m13proyecto1.objects;

import java.io.Serializable;

public class Product implements Serializable {

    public String photo;
    public String name;
    public String descript;
    public int amount;
    public String price;
    public int type_sex;
    public boolean stock;
    public int id_supplier;
    public int product_type;

    public Product() {

    }

    public Product(String productPhoto, String productName, String productDescript, String productPrice) {
        this.photo = productPhoto;
        this.name = productName;
        this.descript = productDescript;
        this.price = productPrice;
    }

    public Product(String productPhoto, String productName, String productPrice) {
        this.photo = productPhoto;
        this.name = productName;
        this.price = productPrice;
    }

    public Product(String productPhoto, String productName, int productAmount, String productPrice, int productTypeSex, boolean productStock, int productIdSupplier, int productType) {
        this.photo = productPhoto;
        this.name = productName;
        this.amount = productAmount;
        this.price = productPrice;
        this.type_sex = productTypeSex;
        this.stock = productStock;
        this.id_supplier = productIdSupplier;
        this.product_type = productType;
    }

    public Product(String productName, int productAmount, String productPrice, int productTypeSex, boolean productStock, int productIdSupplier, int productType) {
        this.name = productName;
        this.amount = productAmount;
        this.price = productPrice;
        this.type_sex = productTypeSex;
        this.stock = productStock;
        this.id_supplier = productIdSupplier;
        this.product_type = productType;
    }

    public Product(String productName, String productPrice) {
        this.name = productName;
        this.price = productPrice;
    }

    public String getImageUrl() {
        return photo;
    }

}
