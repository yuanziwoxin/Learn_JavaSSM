package com.imooc.ioc.demo4;

public class Product {

    private String name;
    private double price;

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    private double size;

    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", category=" + category +
                '}';
    }
}
