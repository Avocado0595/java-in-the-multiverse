package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Config;

public class MySQLDb extends DAOFactory{
	private static String url = Config.getConfig().get("DBURL");
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String user = Config.getConfig().get("DBUSERNAME");
    private static String password = Config.getConfig().get("DBPASSWORD");

	@SuppressWarnings("deprecation")
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
