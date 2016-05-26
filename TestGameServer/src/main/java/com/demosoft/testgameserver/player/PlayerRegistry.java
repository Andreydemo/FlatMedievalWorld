package com.demosoft.testgameserver.player;

import com.demosoft.testgameserver.map.enity.Point;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
@Component
public class PlayerRegistry {

    private int startX = 25;
    private int startY = 25;


    private HashMap<String, Player> onlinePlayers = new HashMap<>();

    public Player putPlayer(String key, Player value) {
        value.setPoint(new Point(startX, startY));
        System.out.println("Player inited");
        return onlinePlayers.put(key, value);
    }
}
