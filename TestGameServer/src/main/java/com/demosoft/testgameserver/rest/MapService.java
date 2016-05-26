package com.demosoft.testgameserver.rest;

import com.demosoft.testgameserver.map.MapComponent;
import com.demosoft.testgameserver.map.enity.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demosoft.testgameserver.map.enity.Cell;

import java.util.Collection;

/**
 * Created by Andrii_Korkoshko on 5/23/2016.
 */
@RestController
@RequestMapping("/map")
public class MapService {

    @Autowired
    MapComponent mapComponent;

    @RequestMapping(value = "/cell/x/{x}/y/{y}")
    public Cell getCellByPosition(@PathVariable("x") int x, @PathVariable("y") int y) {
        return mapComponent.map.getCell(x, y);
    }

    @RequestMapping(value = "/cell/x/{x}/y/{y}/type")
    public Cell.CellType getCelTypeByPosition(@PathVariable("x") int x, @PathVariable("y") int y) {
        return mapComponent.map.getCell(x, y).getCellType();
    }


    @RequestMapping(value = "/cell/all")
    public Map.MapTransportBean getAll() {
        return mapComponent.map.getMapTransportBean();
    }
}
