package services.authentication;

import Server.services.authentication.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RenameDB {
    private Connection connection;
    private ConnectDB connectDB;

    private void setConnection() {
        connectDB = new ConnectDB();
        try {
            connectDB.connection();
            this.connection = connectDB.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void renameUser (String usernameOld, String usernameNew) throws SQLException {
        setConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE auth SET username = ? WHERE username = ?");
        connection.setAutoCommit(false);
        pstmt.setString(1, usernameNew);
        pstmt.setString(2, usernameOld);
        pstmt.addBatch();
        pstmt.executeBatch();
        connection.setAutoCommit(true);
        connectDB.disconnect();
    }
}
