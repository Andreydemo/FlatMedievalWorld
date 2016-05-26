package com.demosoft.testgameserver.map;


import com.demosoft.testgameserver.map.enity.Building;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public interface BuildingFactory {

    Building getCastle();
    Building getHouse();
    Building getWall();
}
