package com.demosoft.testgame;

import com.demosoft.testgame.map.enity.Point;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
@Component
public class Player {

    private String currentImage = "player.png";

    private Point point = new Point(25, 25);

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getCurrentImage() {
        return currentImage;
    }

    public void up() {
        currentImage = "player_up.png";
    }

    public void down() {
        currentImage = "player_down.png";
    }

    public void left() {
        currentImage = "player_left.png";
    }

    public void right() {
        currentImage = "player_right.png";
    }
}
