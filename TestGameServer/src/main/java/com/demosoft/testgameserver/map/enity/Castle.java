package com.demosoft.testgameserver.map.enity;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public class Castle extends Building {
    public Castle(Cell firstCell) {
        super(firstCell);
    }

    @Override
    public void upgrade() {
        setLevel(getLevel() + 1);
    }
}
