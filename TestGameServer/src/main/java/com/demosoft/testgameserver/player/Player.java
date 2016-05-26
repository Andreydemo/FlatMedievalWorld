package com.demosoft.testgameserver.player;

import com.demosoft.testgameserver.map.enity.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Player {

    @Autowired
    private PlayerRegistry playerRegistry;

    private Point point = new Point();

    private String id;

    public Calendar lastActivity;

    public Player() {
        id = UUID.randomUUID().toString();
    }

    @PostConstruct
    public void init() {
        playerRegistry.putPlayer(id, this);

    }

    public Calendar getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Calendar lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void put(Player player){
        this.id = player.id;
        this.point = player.point;
    }
}
