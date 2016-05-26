package com.demosoft.testgameserver.service;

import com.demosoft.testgameserver.player.Player;
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
public class PlayerService {

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

    public static final String PLAYER_COLLECTION = "players";


    public Player createPlayer() {
        Player player = new Player();
        mongoTemplate.insert(player, PLAYER_COLLECTION);
        return player;
    }

    public Player getPlayerById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Player.class, PLAYER_COLLECTION);
    }
}
