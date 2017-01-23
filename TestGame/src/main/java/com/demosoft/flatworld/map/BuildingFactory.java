package com.demosoft.flatworld.map;

import com.demosoft.flatworld.map.enity.Building;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public interface BuildingFactory {

    Building getCastle();
    Building getHouse();
    Building getWall();
}
