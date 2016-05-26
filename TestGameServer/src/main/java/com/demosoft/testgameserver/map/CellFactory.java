package com.demosoft.testgameserver.map;


import com.demosoft.testgameserver.map.enity.Cell;
import com.demosoft.testgameserver.map.enity.Point;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public interface CellFactory {

    Cell getCell(Cell.CellType cellType);

    Cell getCell(Cell.CellType cellType, Point point);


}
