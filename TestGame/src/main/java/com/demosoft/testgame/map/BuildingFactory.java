package com.demosoft.testgame.map;

import com.demosoft.testgame.map.enity.Building;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public interface BuildingFactory {

    Building getCastle();
    Building getHouse();
    Building getWall();
}
