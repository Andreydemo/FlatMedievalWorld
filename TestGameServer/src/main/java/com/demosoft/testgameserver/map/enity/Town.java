package com.demosoft.testgameserver.map.enity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public class Town {

    private List<Building> buildings = new ArrayList<>();

    private Castle castle;

    public int getBorderRadius() {
        return castle.getLevel() * 3;
    }

}
