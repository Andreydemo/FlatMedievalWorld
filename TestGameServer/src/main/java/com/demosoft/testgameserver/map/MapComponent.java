package com.demosoft.testgameserver.map;

import com.demosoft.testgameserver.map.enity.Map;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Andrii_Korkoshko on 5/23/2016.
 */
@Component
public class MapComponent {

    public Map map;

    @PostConstruct
    public void init() {
        map = MapUtils.generateMap(100, 17);
    }
}
