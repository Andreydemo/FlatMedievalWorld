package com.demosoft.testgame.map.service;

import com.demosoft.testgame.map.enity.Cell;
import com.demosoft.testgame.map.enity.Map;
import com.demosoft.testgame.map.enity.Point;
import com.demosoft.testgame.rest.MapReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Andrii_Korkoshko on 5/25/2016.
 */
@Component
public class MapService {

    @Autowired
    private MapReceiver mapReceiver;

    private Map map;

    @PostConstruct
    public void init() {
        map = mapReceiver.getMap();
    }


    public Cell[][] getPoints(Point point, int rad) {
        int length = 1 + rad * 2;

        Cell[][] points = new Cell[length][];
        for (int i = 0; i < length; i++) {
            points[i] = new Cell[length];
            System.arraycopy(map.getCells()[point.getX() - rad + i], point.getY() - rad, points[i], 0, length);
        }
        return points;
    }


}
