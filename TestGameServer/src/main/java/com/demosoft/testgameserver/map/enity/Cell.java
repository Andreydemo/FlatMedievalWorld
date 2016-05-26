package com.demosoft.testgameserver.map.enity;


import java.awt.*;


/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public class Cell {

    public static int lenght = 50;

    public enum CellType {
        GROUND(Color.lightGray, "▒"), SAND(Color.YELLOW, "2"), SEA(Color.BLUE, "░"), RIVER(Color.cyan, "3"), GRASS(Color.GREEN, "4"), BUILDING(Color.black, "5");

        private Color color;
        private String code;

        CellType(Color color, String code) {
            this.color = color;
            this.code = code;
        }

        public Color getColor() {
            return color;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    private Point point;
    private CellType cellType;

    public Cell(CellType cellType) {
        this.cellType = cellType;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }


    @Override
    public String toString() {
        return cellType + " x:" + point.getX() + " y:" + point.getY();
    }


    public CellType getCellType() {
        return cellType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return point != null ? point.equals(cell.point) : cell.point == null;

    }

    @Override
    public int hashCode() {
        return point != null ? point.hashCode() : 0;
    }
}
