package services;

import java.sql.SQLException;

public interface AuthenticationServiceDB {
    String getUsernameByLoginAndPassword(String login, String password) throws SQLException;
}
