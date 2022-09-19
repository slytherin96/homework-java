package Server.services.impl;

import Server.models.User;
import Server.services.AuthenticationService;


import java.util.List;

public class SimpleAuthenticationServiceImpl implements AuthenticationService {
    private static List<User> clients = List.of(
            new User("martin", "1111", "Martin_Superstar"),
            new User("batman", "2222", "Брюс_Уэйн"),
            new User("gena", "3333", "Гендальф_Серый"),
            new User("mario", "4444", "Super_Mario"),
            new User("bender", "5555", "Bender"),
            new User("ezhik", "6666", "Super_Sonic")
    );


    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        for (User client : clients) {
            if (client.getLogin().equals(login) && client.getPassword().equals(password) ) {
                return client.getUsername();
            }
        }
        return null;
    }
}
