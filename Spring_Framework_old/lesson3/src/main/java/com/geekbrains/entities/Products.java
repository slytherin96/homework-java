package com.geekbrains.entities;

public class Products {
    private Product[] products;

    public Product[] getProducts() {
        return products;
    }
    public Product getSelectedProducts(int id){
        for (Product product:products){
            if (product.getId()==id){
                return product;
            }
        }
        return null;
    }
    public void setProducts(Product[] products) {
        this.products = products;
    }
}
