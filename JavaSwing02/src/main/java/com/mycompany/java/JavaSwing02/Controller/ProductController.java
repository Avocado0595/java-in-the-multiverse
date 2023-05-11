package com.mycompany.java.JavaSwing02.Controller;

import com.mycompany.java.JavaSwing02.Model.Product;
import com.mycompany.java.JavaSwing02.Model.ProductDAO;
import com.mycompany.java.JavaSwing02.View.ProductView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductController {
    private ProductDAO productDAO;
    private ProductView productView;

    public ProductController(ProductView productView) {
        this.productDAO = new ProductDAO();
        this.productView = productView;
        initAction();
    }
    private void initAction(){
        productView.addAddProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product product = productView.getProductData();
                    boolean isSuccess = productDAO.add(product);
                    if (isSuccess) {
                        productView.showMessage("Add new product successfully");
                        showProductView();
                    } else {
                        productView.showMessage("Add new product fail");
                    }
                }catch (Exception exc){
                    productView.showMessage("Add new product fail\nMessage: "+exc.getMessage());
                }
            }
        });

        productView.addSelectTableListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                productView.fillSelectTable();
            }
        });

        productView.addEditProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product product = productView.getProductData();
                    boolean isSuccess = productDAO.update(product);
                    if (isSuccess) {
                        productView.showMessage("Update product successfully");
                        showProductView();
                    } else {
                        productView.showMessage("Update product fail");
                    }
                }catch (Exception exc){
                    productView.showMessage("Update product fail\nMessage: "+exc.getMessage());
                }
            }
        });

        productView.addDeleteProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product product = productView.getProductData();
                    boolean isSuccess = productDAO.delete(product.getId());
                    if (isSuccess) {
                        productView.showMessage("Delete product id="+product.getId());
                        showProductView();
                    } else {
                        productView.showMessage("Delete product fail");
                    }
                }catch (Exception exc){
                    productView.showMessage("Delete product fail\nMessage: "+exc.getMessage());
                }
            }
        });
    }
    public void showProductView(){
        List<Product> productList = productDAO.getAll();
        productView.showListProduct(productList);
        productView.setVisible(true);
    }
}
