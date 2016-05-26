package com.demosoft.testgameserver.map.enity;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrii_Korkoshko on 5/23/2016.
 */
public class Map {

    private java.util.Map<Point, Cell> map = new HashMap<>();
    private java.util.Map<Point, Cell> groundIndex = new HashMap<>();
    private Set<Cell> groundList = new HashSet<>();
    private Cell[][] cells;
    private int length;


    public Map(Cell[][] cells) {
        this.cells = cells;
        for (Cell[] cellsRef1 : cells) {
            for (Cell cellRef2 : cellsRef1) {
                putCell(cellRef2);
            }
        }
        length = cells[0].length;
    }

    public Map() {
    }

    public Cell putCell(Cell cell) {
        Cell existingCell = map.get(cell.getPoint());
        map.put(cell.getPoint(), cell);
        cells[cell.getPoint().getX()][cell.getPoint().getY()] = cell;
        processIndexes(cell);
        return existingCell;
    }

    public Cell getCell(Point point) {
        return map.get(point);
    }

    public Cell getCell(int x, int y) {
        return getCell(new Point(x, y));
    }

    public Collection<Cell> getAllCells() {
        return map.values();
    }

    public void processGroundIndex(Cell cell) {
        if (cell.getCellType().equals(Cell.CellType.GROUND)) {
            groundIndex.put(cell.getPoint(), cell);
            groundList.add(cell);
        }
    }

    public void processIndexes(Cell cell) {
        processGroundIndex(cell);
    }

    public void updateIndex() {
        for (java.util.Map.Entry<Point, Cell> pointCellEntry : map.entrySet()) {
            processIndexes(pointCellEntry.getValue());
        }
    }

    public int getLength() {
        return length;
    }

    @Deprecated
    public Cell[][] getCells() {
        return cells;
    }

    public MapTransportBean getMapTransportBean(){
        MapTransportBean mapTransportBean = new MapTransportBean();
        mapTransportBean.setCells(cells);
        mapTransportBean.setGroundIndex(groundIndex);
        mapTransportBean.setGroundList(groundList);
        mapTransportBean.setLenght(length);
        return mapTransportBean;
    }

    public static class MapTransportBean{
        private java.util.Map<Point, Cell> map = new HashMap<>();
        private java.util.Map<Point, Cell> groundIndex = new HashMap<>();
        private Set<Cell> groundList = new HashSet<>();
        private Cell[][] cells;
        private int lenght;

        public java.util.Map<Point, Cell> getMap() {
            return map;
        }

        public void setMap(java.util.Map<Point, Cell> map) {
            this.map = map;
        }

        public java.util.Map<Point, Cell> getGroundIndex() {
            return groundIndex;
        }

        public void setGroundIndex(java.util.Map<Point, Cell> groundIndex) {
            this.groundIndex = groundIndex;
        }

        public Set<Cell> getGroundList() {
            return groundList;
        }

        public void setGroundList(Set<Cell> groundList) {
            this.groundList = groundList;
        }

        public Cell[][] getCells() {
            return cells;
        }

        public void setCells(Cell[][] cells) {
            this.cells = cells;
        }

        public int getLenght() {
            return lenght;
        }

        public void setLenght(int lenght) {
            this.lenght = lenght;
        }
    }
}
