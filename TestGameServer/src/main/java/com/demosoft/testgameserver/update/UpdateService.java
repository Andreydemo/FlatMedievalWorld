package com.demosoft.testgameserver.update;

import com.demosoft.testgameserver.map.enity.*;
import com.demosoft.testgameserver.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
@Component
public class UpdateService {


    @Autowired
    private EventManager eventManager;

    public void updatePosition(Player player, int diffX, int diffY) {
        Event.PlayerEvent event = new Event.PlayerEvent();
        Point point = player.getPoint();
        event.setStart(point);
        point = new Point(point.getX() + diffX, point.getY() + diffY);
        event.setEnd(point);
        player.setPoint(point);
        player.setSnapshot(eventManager.pushNewEvent(event).getNumber());
    }

}
