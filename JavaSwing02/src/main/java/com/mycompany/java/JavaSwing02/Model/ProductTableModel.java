package com.mycompany.java.JavaSwing02.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    private String[] columnNames = new String[] { "ID", "NAME", "QUANTITY"};
    private List<Product> productList;
    public ProductTableModel(){
        productList = new ArrayList<>();
    }
    public void setData(List<Product> productList){
        this.productList = productList;
        fireTableDataChanged();
    }
    @Override
    public String getColumnName(int i){
        return columnNames[i];
    }
    @Override
    public int getRowCount() {
        return productList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Product product = productList.get(row);
        switch(col){
            case 0:
                return product.getId();
            case 1:
                return product.getName();
            case 2:
                return product.getQuantity();
            default:
                return null;
        }
    }
}
