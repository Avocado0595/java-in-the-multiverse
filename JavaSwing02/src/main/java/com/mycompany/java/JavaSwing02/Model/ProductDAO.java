package com.mycompany.java.JavaSwing02.Model;

import com.mycompany.java.JavaSwing02.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public ProductDAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error: "+e);
        }
    }
    public Connection getConnection(){
        String url = Config.getDbConfig().get("DBURL");
        try{
            Connection con = (Connection) DriverManager.getConnection(url, Config.getDbConfig().get("DBUSERNAME"),Config.getDbConfig().get("DBPASSWORD"));
            System.out.println("Connect db successfully!");
            return con;
        }
        catch(SQLException e){
            System.out.println("Connect db fail!");
            throw new RuntimeException(e);
        }
    }
    private void closeConnection(Connection con){
        try{
            con.close();
            System.out.println("Db closed");
        }
        catch(SQLException e){
            System.out.println("Can not close db.");
        }
    }
    public List<Product> getAll(){
        Connection con = getConnection();
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try{
            Statement selectStatement = con.createStatement();
            ResultSet result = selectStatement.executeQuery(sql);
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                int quantity = result.getInt("quantity");
                productList.add(new Product(id, name, quantity));
            }
            selectStatement.close();
            result.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            closeConnection(con);
        }
        return productList;
    }
    public boolean add(Product product){
        Connection con = getConnection();
        List<Product> productList = new ArrayList<>();
        String sql = "INSERT INTO product(name, quantity) VALUES(?,?)";
        try{
            PreparedStatement insertStatement = con.prepareStatement(sql);
            insertStatement.setString(1,product.getName());
            insertStatement.setInt(2,product.getQuantity());
            insertStatement.executeUpdate();
            insertStatement.close();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally {
            closeConnection(con);
        }
    }
    public boolean delete(int id){
        Connection con = getConnection();
        String sql = "DELETE FROM product where id=?";
        try{
            PreparedStatement deleteStatement = con.prepareStatement(sql);
            deleteStatement.setInt(1,id);
            deleteStatement.executeUpdate();
            deleteStatement.close();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally {
            closeConnection(con);
        }
    }
    public boolean update(Product product){
        Connection con = getConnection();
        String sql = "UPDATE product SET name=?, quantity=? WHERE id=?";
        try{
            PreparedStatement updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1,product.getName());
            updateStatement.setInt(2,product.getQuantity());
            updateStatement.setInt(3,product.getId());
            updateStatement.executeUpdate();
            updateStatement.close();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally {
            closeConnection(con);
        }

    }

}
