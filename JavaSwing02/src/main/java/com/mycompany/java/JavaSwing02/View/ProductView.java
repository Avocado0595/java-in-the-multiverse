/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java.JavaSwing02.View;

import com.mycompany.java.JavaSwing02.Model.Product;
import com.mycompany.java.JavaSwing02.Model.ProductTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;


/**
 *
 * @author Admins
 */
public class ProductView extends JFrame {
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JLabel lbId;
    private JLabel lbName;
    private JLabel lbQuantity;
    private JPanel pn1;
    private JPanel pn2;
    private JPanel pnMenu;
    private JPanel pnInfo;
    private JScrollPane spnTable;
    private JTable tbProduct;
    private JTextField txtName;
    private JTextField txtId;
    private JSpinner spinnerQuantity;
    private ProductTableModel tableModel;
    public ProductView(String title){
        setTitle(title);
        initComponents();
    }
    private void initComponents(){
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pn1 = new javax.swing.JPanel();
        pn2 = new javax.swing.JPanel();
        pnMenu = new javax.swing.JPanel();
        lbId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbQuantity = new javax.swing.JLabel();

        SpinnerNumberModel spinnerModel;
        Integer current = 0;
        Integer min = 0;
        Integer max = Integer.MAX_VALUE;
        Integer step = 1;
        spinnerModel = new SpinnerNumberModel(current, min, max, step);
        spinnerQuantity = new JSpinner(spinnerModel);
        //
        pnInfo = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        spnTable = new javax.swing.JScrollPane();
        tbProduct = new javax.swing.JTable();

        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.BorderLayout());

        pn1.setPreferredSize(new java.awt.Dimension(818, 78));

        javax.swing.GroupLayout pn1Layout = new javax.swing.GroupLayout(pn1);
        pn1.setLayout(pn1Layout);
        pn1Layout.setHorizontalGroup(
            pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pn1Layout.setVerticalGroup(
                pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(pn1, java.awt.BorderLayout.PAGE_END);

        pn2.setLayout(new java.awt.BorderLayout());

        pnInfo.setLayout(new java.awt.GridLayout(3, 2, 1, 1));

        lbId.setText("ID");
        pnInfo.add(lbId);

        txtId.setText("");
        pnInfo.add(txtId);

        lbName.setText("Name");
        pnInfo.add(lbName);

        txtName.setText("");
        pnInfo.add(txtName);

        lbQuantity.setText("Quantity");
        pnInfo.add(lbQuantity);
        pnInfo.add(spinnerQuantity);
        pn2.add(pnInfo, java.awt.BorderLayout.SOUTH);
        pnInfo.getAccessibleContext().setAccessibleName("");

        btnAdd.setText("ADD");
        pnMenu.add(btnAdd);

        btnUpdate.setText("UPDATE");
        pnMenu.add(btnUpdate);

        btnDelete.setText("DELETE");
        pnMenu.add(btnDelete);


        pn2.add(pnMenu, java.awt.BorderLayout.CENTER);
        getContentPane().add(pn2, java.awt.BorderLayout.PAGE_END);
        tableModel = new ProductTableModel();
        tbProduct.setModel(tableModel);
        spnTable.setViewportView(tbProduct);
        getContentPane().add(spnTable, java.awt.BorderLayout.CENTER);
        pack();
    }
    public void showListProduct(List<Product> productList){
        tableModel.setData(productList);
    }
    public void addAddProductListener(ActionListener listener){
        btnAdd.addActionListener(listener);
    }
    public void addSelectTableListener(ListSelectionListener listener){
        tbProduct.getSelectionModel().addListSelectionListener(listener);
    }
    public void addEditProductListener(ActionListener listener){
        btnUpdate.addActionListener(listener);
    }
    public void addDeleteProductListener(ActionListener listener){
        btnDelete.addActionListener(listener);
    }
    private int checkValidQuantity(String quantity){
        try{
            if(Integer.parseInt(quantity)<0)
                throw new Exception("Quantity should be positive!");
            else
                return Integer.parseInt(quantity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Product getProductData(){
            int id;
            try {
                id = Integer.parseInt(txtId.getText());
            }
            catch(Exception e){
                id = 0;
            }
            String name = txtName.getText();
            int quantity = checkValidQuantity(spinnerQuantity.getValue().toString());
            Product product = new Product(id,name, quantity);
            return product;

    }
    public void showMessage(String message){
        showMessageDialog(this,message);
    }
    public void fillSelectTable(){
        int row = tbProduct.getSelectedRow();
        if(row>=0){
            txtId.setText(tbProduct.getValueAt(row,0).toString());
            txtName.setText(tbProduct.getValueAt(row,1).toString());
            spinnerQuantity.setValue(Integer.parseInt(tbProduct.getValueAt(row,2).toString()));
        }
    }
}
