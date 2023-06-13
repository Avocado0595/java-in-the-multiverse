package model;

import java.sql.Connection;

public abstract class DAOFactory {
	public abstract Connection openConnection();
    public static DAOFactory getDatabase() {
        return new MySQLDb();
    }
}
