package org.example.model.DAO.daoFactory;

import org.example.Config;
import org.example.model.DAO.concrete.MySQLStudent;
import org.example.model.DAO.interfaces.StudentDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL extends DaoFactory {
    private static String url =  Config.getDbConfig().get("DBURL");
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String user = Config.getDbConfig().get("DBUSERNAME");;
    private static String password = Config.getDbConfig().get("DBPASSWORD");;

    public Connection openConnection() {
        try {
            Class.forName(driver).newInstance();
            return DriverManager.getConnection(url , user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex){
            System.err.println(
                    "Connect db fail!" + ex);
        }
        return null;
    }
    public void closeConnection(Connection con){
        try{
            con.close();
            System.out.println("Db closed");
        }
        catch(SQLException e){
            System.out.println("Can not close db.");
        }
    }

}
