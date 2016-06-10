package com.demosoft.testgameserver.service;

import com.demosoft.testgameserver.player.Player;
import com.demosoft.testgameserver.service.entiry.ServiceResponse;
import com.demosoft.testgameserver.service.entiry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
@Component
public class AuthenticationService {

    @Autowired
    private PlayerService playerService;

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

    public static final String USER_COLLECTION = "users";


    public ServiceResponse<User> register(User user) {
        ServiceResponse<User> serviceResponse = new ServiceResponse();
        if (isUserExist(user)) {
            serviceResponse.setSuccess(false);
            serviceResponse.getErrors().add("user exists");
            return serviceResponse;
        }
        Player player = playerService.createPlayer();
        user.setPlayerId(player.getId());
        mongoTemplate.insert(user, USER_COLLECTION);
        serviceResponse.setSuccess(true);
        serviceResponse.setObject(user);
        return serviceResponse;
    }

    public ServiceResponse<User> login(User user, Profile profile) {
        ServiceResponse serviceResponse = new ServiceResponse();
        User user1 = getUserByLogin(user);
        if (user1 == null) {
            serviceResponse.setSuccess(false);
            serviceResponse.getErrors().add("login or password wrong");
            return serviceResponse;
        }
        if (!user1.getPassword().equals(user.getPassword())) {
            serviceResponse.setSuccess(false);
            serviceResponse.getErrors().add("login or password wrong");
            return serviceResponse;
        }
        serviceResponse.setSuccess(true);
        profile.setUser(user1);
        serviceResponse.setObject(user1);
        return serviceResponse;
    }


    public boolean isUserExist(User user) {
        User existUser = mongoTemplate.findOne(new Query(Criteria.where("login").is(user.getLogin())), User.class, USER_COLLECTION);
        return existUser != null;
    }

    public User getUserByLogin(User user) {
        User existUser = mongoTemplate.findOne(new Query(Criteria.where("login").is(user.getLogin())), User.class, USER_COLLECTION);
        return existUser;
    }


}
