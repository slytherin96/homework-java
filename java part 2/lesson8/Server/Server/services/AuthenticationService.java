package Server.services;

public interface AuthenticationService {
    String getUsernameByLoginAndPassword(String login, String password);

}
