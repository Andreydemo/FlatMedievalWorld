package com.demosoft.testgameserver.map.enity;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public abstract class Building {

    private Cell firstCell;
    private int width;
    private int height;
    private int level = 1;

    public Building(Cell firstCell) {
        this.firstCell = firstCell;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract void upgrade();
}
