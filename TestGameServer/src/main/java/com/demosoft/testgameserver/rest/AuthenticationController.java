package com.demosoft.testgameserver.rest;

import com.demosoft.testgameserver.player.Player;
import com.demosoft.testgameserver.service.AuthenticationService;
import com.demosoft.testgameserver.service.PlayerService;
import com.demosoft.testgameserver.service.Profile;
import com.demosoft.testgameserver.service.entiry.ServiceResponse;
import com.demosoft.testgameserver.service.entiry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private Profile profile;


    @RequestMapping(value = "/login/{login}/{password}", method = RequestMethod.POST)
    public ServiceResponse<Player> login(@PathVariable("login") String login, @PathVariable("password") String password) {
        ServiceResponse<Player> playerServiceResponse = new ServiceResponse<>();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        ServiceResponse authenticationServiceResponse = authenticationService.login(user, profile);
        playerServiceResponse.setSuccess(authenticationServiceResponse.isSuccess());
        playerServiceResponse.setErrors(authenticationServiceResponse.getErrors());
        if (authenticationServiceResponse.isSuccess()) {
            Player player = playerService.getPlayerById(profile.getUser().getPlayerId());
            profile.getPlayer().put(player);
            profile.setLogedIn(true);
            playerServiceResponse.setObject(player);
        }
        return playerServiceResponse;
    }

    @RequestMapping(value = "/reg/{login}/{password}", method = RequestMethod.POST)
    public ServiceResponse<Player> registration(@PathVariable("login") String login, @PathVariable("password") String password) {
        ServiceResponse<Player> playerServiceResponse = new ServiceResponse<>();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        ServiceResponse<User> authenticationServiceResponse = authenticationService.register(user);
        playerServiceResponse.setSuccess(authenticationServiceResponse.isSuccess());
        playerServiceResponse.setErrors(authenticationServiceResponse.getErrors());
        if (authenticationServiceResponse.isSuccess()) {
            profile.setLogedIn(true);
            playerServiceResponse.setObject(playerService.getPlayerById(authenticationServiceResponse.getObject().getPlayerId()));
        }
        return playerServiceResponse;
    }

}
