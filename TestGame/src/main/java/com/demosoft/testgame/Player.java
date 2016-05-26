package com.demosoft.testgame;

import com.demosoft.testgame.map.enity.Cell;
import com.demosoft.testgame.map.enity.Point;
import com.demosoft.testgame.map.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
@Component
public class Player {

    private String currentImage = "player.png";

    @Autowired
    private
    MapService mapService;

    private String jsessionid;

    private Point point = new Point(25, 25);
    private TactGenerator.TactToken tactToken = TactGenerator.getTactToken();

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
        if (TactGenerator.isTheNextTact(tactToken) && isCanMoveTo(new Point(point.getX(), point.getY() - 1))) {
            point.setY(point.getY() - 1);
        }
    }

    public void down() {
        currentImage = "player_down.png";
        if (TactGenerator.isTheNextTact(tactToken) && isCanMoveTo(new Point(point.getX(), point.getY() + 1))) {
            point.setY(point.getY() + 1);
        }
    }

    public void left() {
        currentImage = "player_left.png";
        if (TactGenerator.isTheNextTact(tactToken) && isCanMoveTo(new Point(point.getX() - 1, point.getY()))) {
            point.setX(point.getX() - 1);
        }
    }

    public void right() {
        currentImage = "player_right.png";
        if (TactGenerator.isTheNextTact(tactToken) && isCanMoveTo(new Point(point.getX() + 1, point.getY()))) {
            point.setX(point.getX() + 1);
        }
    }

    public boolean isCanMoveTo(Point point) {
        Cell cell = mapService.getCell(point);
        return cell.getCellType().isCanMove();
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
