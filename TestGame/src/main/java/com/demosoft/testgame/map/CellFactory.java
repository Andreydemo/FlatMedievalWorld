package com.demosoft.testgame.map;

import com.demosoft.testgame.map.enity.Cell;
import com.demosoft.testgame.map.enity.Point;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public interface CellFactory {

    Cell getCell(Cell.CellType cellType);

    Cell getCell(Cell.CellType cellType, Point point);


}
