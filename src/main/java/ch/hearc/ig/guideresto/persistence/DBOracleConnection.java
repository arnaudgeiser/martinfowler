package ch.hearc.ig.guideresto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOracleConnection {

    private static final String DBURL = "jdbc:oracle:thin:@db.ig.he-arc.ch:1521:ens";
    private static final String DBUSER = "romain_jysch";
    private static final String DBPWD = "romain_jysch";
    private static Connection cnn = null;

    public static Connection openConnection() throws SQLException {
        try {
            // Se connecte à la BD Oracle :
            cnn = DriverManager.getConnection(DBURL, DBUSER, DBPWD);
            // Désactive le mode autocommit :
            cnn.setAutoCommit(false);
            // Niveau d'isolation de JDBC :
            // Niveau d'isolation déterminé par une constante de l'interface Connection :
            //cnn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            return cnn;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static void commit() throws SQLException {
        try {
            cnn.commit();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static void closeConnection() throws SQLException {
        try {
            cnn.close();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

}
