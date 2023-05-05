package com.example.UserPizzaService.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pizza {

    @Id
    private int pizzaId;
    private String pizzaName;
    private double pizzaPrice;
    private int pizzaQuantity;
    private String pizzaSize;

    public Pizza() {
    }

    public Pizza(int pizzaId, String pizzaName, double pizzaPrice, int pizzaQuantity, String pizzaSize) {
        this.pizzaId = pizzaId;
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
        this.pizzaQuantity = pizzaQuantity;
        this.pizzaSize = pizzaSize;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public int getPizzaQuantity() {
        return pizzaQuantity;
    }

    public void setPizzaQuantity(int pizzaQuantity) {
        this.pizzaQuantity = pizzaQuantity;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaId=" + pizzaId +
                ", pizzaName='" + pizzaName + '\'' +
                ", pizzaPrice=" + pizzaPrice +
                ", pizzaQuantity=" + pizzaQuantity +
                ", pizzaSize='" + pizzaSize + '\'' +
                '}';
    }
}
