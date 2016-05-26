package com.demosoft.testgameserver.service.entiry;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
public class User {

    private String login;
    private String password;
    private String playerId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
