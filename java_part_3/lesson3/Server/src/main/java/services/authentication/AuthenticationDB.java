package services.authentication;

import Server.services.authentication.ConnectDB;
import services.AuthenticationServiceDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AuthenticationDB implements AuthenticationServiceDB  {

    private ConnectDB connectDB;

    private Connection connection;

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

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) throws SQLException {
        setConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM auth where login = ? and password = ?");
        pstmt.setString(1, login);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        String username = rs.getString("username");
        connectDB.disconnect();
        return username;
    }
}
