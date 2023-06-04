package org.example.model.DAO.daoFactory;

import java.sql.Connection;

public abstract class DaoFactory {

    /*
     * There will be a method for each DAO that can be
     * created. The concrete factories will have to
     * implement these methods.
     */
    public abstract Connection openConnection();
    public static DaoFactory getDatabase() {
        return new MySQL();
    }
}
