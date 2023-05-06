package org.example;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
       View v = new View();
       Model m = new Model();
       new Controller(v,m,0);

    }
}