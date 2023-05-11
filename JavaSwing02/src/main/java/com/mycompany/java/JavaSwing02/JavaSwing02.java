/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.java.JavaSwing02;

import com.mycompany.java.JavaSwing02.Controller.ProductController;
import com.mycompany.java.JavaSwing02.View.ProductView;

public class JavaSwing02 {

    public static void main(String[] args) {
        ProductController controller = new ProductController(new ProductView("Product Manager"));
        controller.showProductView();
    }
}
