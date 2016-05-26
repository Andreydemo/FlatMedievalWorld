package com.demosoft.testgameserver.map;


import com.demosoft.testgameserver.map.enity.Cell;
import com.demosoft.testgameserver.map.enity.Map;
import com.demosoft.testgameserver.map.enity.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrii_Korkoshko on 5/23/2016.
 */
public class MapUtils {

    public static Map generateMap(int size, int continentCount) {
        return generateGround(MapUtils.generateSea(size), continentCount);
    }

    public static List<Cell> getNeightbosrs(Cell cell, Cell.CellType cellType, Map map) {
        List<Point> points = getNeightbosrPoints(cell.getPoint());
        List<Cell> results = new ArrayList<>();
        Cell tmp = null;
        for (Point point : points) {
            tmp = map.getCell(point);
            if (tmp != null && tmp.getCellType().equals(cellType)) {
                results.add(tmp);
            }
        }
        return results;
    }

    public static List<Point> getNeightbosrPoints(Point point) {
        List<Point> result = new ArrayList<>();
        Point temporary = null;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                temporary = getPointArount(point, i, j);
                if (isValidPoint(temporary)) {
                    result.add(temporary);
                }
            }
        }
        return result;
    }

    public static boolean isValidPoint(Point point) {
        if (point.getX() < 0) {
            return false;
        }
        if (point.getY() < 0) {
            return false;
        }
        return true;
    }

    public static Point getPointArount(Point point, int x, int y) {
        return new Point(point.getX() - x, point.getY() - y);
    }

    public static Map generateSea(int size) {
        Cell[][] cells = new Cell[size][];
        for (int i = 0; i < size; i++) {
            cells[i] = new Cell[size];
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(Cell.CellType.SEA);
                cells[i][j].setPoint(new Point(i, j));

            }
        }
        return new Map(cells);
    }

    public static Map generateGround(Map cells, int coninentCount) {
        double sqrt = Math.sqrt(coninentCount);
        int res = (int) sqrt; //целая часть
        double res2 = sqrt - res; //дробная часть
        if (res2 > 0) {
            sqrt = res + 1;
        }
        List<Cell> startPoint = getRandomCells(getStartContinentPoints(cells, (int) sqrt), coninentCount);
        List<List<Cell>> continents = new ArrayList<>();

        for (Cell startCell : startPoint) {
            startCell.setCellType(Cell.CellType.GROUND);
            List<Cell> continent = new ArrayList<>();
            continent.add(startCell);
            continents.add(continent);
        }
        generateGroundCells(continents, cells, 19);

        return cells;
    }

    public static void generateGroundCells(List<List<Cell>> continents, Map map, int count) {
        for (int i = 0; i < count; i++) {
            generateGroundCells(continents, map);
        }
    }

    public static void generateGroundCells(List<List<Cell>> continents, Map map) {
        for (List<Cell> continent : continents) {
            List<Cell> toAdd = new ArrayList<>();
            for (Cell cell : continent) {
                List<Cell> neighbors = MapUtils.getNeightbosrs(cell, Cell.CellType.SEA, map);
                if (neighbors.size() > 3) {
                    Cell newGroundCell = getRandomCells(neighbors, 3).get(0);
                    newGroundCell.setCellType(Cell.CellType.GROUND);
                    map.processIndexes(cell);
                    toAdd.add(newGroundCell);
                }
            }
            continent.addAll(toAdd);
        }
    }

    public static List<Cell> getRandomCells(List<Cell> cells, int count) {
        if (cells.size() < count) {
            throw new IllegalArgumentException("cells.size() < count");
        }
        if (cells.size() == count) {
            System.out.println("cells.size() == count ");
            return new ArrayList<>(cells);
        }
        List<Cell> result = new ArrayList<>();
        Set<Integer> usedIndexase = new HashSet<>();
        int stepCounter = 0;
        while (result.size() < count) {
            stepCounter++;
            if (stepCounter > 100) {
                System.out.println("Terminated by step counter " + result.size() + " < " + count);
                break;
            }
            int index = ThreadLocalRandom.current().nextInt(count);
            if (usedIndexase.contains(index)) {
                index++;
                if (usedIndexase.contains(index)) {
                    continue;
                } else {
                    usedIndexase.add(index);
                }
            } else {
                usedIndexase.add(index);
            }
            result.add(cells.get(index));
        }
        return result;
    }

    public static List<Cell> getStartContinentPoints(Map map, int sqrt) {
        List<Cell> cells = new ArrayList<>();

        for (int i = 1; i <= sqrt; i++) {
            for (int j = 1; j <= sqrt; j++) {

                Point point = new Point((map.getLength() / sqrt) * i - 1 - (map.getLength() / sqrt) / 2, (map.getLength() / sqrt) * j - 1 - (map.getLength() / sqrt) / 2);

                Cell startPoint = map.getCell(point);
                System.out.println(startPoint);
                cells.add(startPoint);
            }
        }
        return cells;
    }


    public static void print(Map cells) {
        for (Cell[] cell1 : cells.getCells()) {

            for (Cell cell2 : cell1) {
                System.out.print(cell2.getCellType().getCode() + " ");
            }
            System.out.println();
        }
    }
}
