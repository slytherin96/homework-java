package services.authentication;

import Server.services.authentication.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDB {
    private Connection connection;
    private ConnectDB connectDB;

    private void setConnection() {
        connectDB = new ConnectDB();

        try {
            connectDB.connection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.connection = connectDB.getConnection();

    }
    public void addUser (String login, String password, String username) throws SQLException {
        setConnection();
        PreparedStatement pstmt = connection.prepareStatement("insert into auth(login, password, username) values (?, ?, ?)");
        connection.setAutoCommit(false);
        pstmt.setString(1, login);
        pstmt.setString(2, password);
        pstmt.setString(3, username);
        pstmt.addBatch();
        pstmt.executeBatch();
        connection.setAutoCommit(true);
        connectDB.disconnect();
    }
}
