package org.example;

import org.example.ChicagoStyle.ChicagoPizzaStore;
import org.example.NYStyle.NYPizzaStore;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to PizzaStore!");
        PizzaStore nyStore = new NYPizzaStore();
        nyStore.orderPizza("cheese");

        PizzaStore cgStore = new ChicagoPizzaStore();
        cgStore.orderPizza("pepperoni");
    }
}