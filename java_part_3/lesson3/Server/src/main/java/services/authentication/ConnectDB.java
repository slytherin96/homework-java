package Server.services.authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB  {
   private Connection connection;
   private Statement stmt;

    public Statement getStmt() {
        return stmt;
    }
    public Connection getConnection() {
        return connection;
    }

    public void connection() throws ClassNotFoundException, SQLException {
       Class.forName("org.sqlite.JDBC");
       connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/mainDB.db");

       stmt = connection.createStatement();
   }

   public void disconnect() throws SQLException {
       connection.close();
   }


}
