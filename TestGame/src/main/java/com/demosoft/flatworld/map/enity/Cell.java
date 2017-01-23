package com.demosoft.flatworld.map.enity;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import static com.demosoft.flatworld.map.MapUtils.*;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public class Cell implements Serializable {

    public static int lenght = 50;

    public Cell() {
    }

    public enum CellType {
        GROUND(Color.orange, "▒", true, "ground.png"), SAND(Color.YELLOW, "2", true), SEA(Color.BLUE, "░", false, "sea.png"), RIVER(Color.cyan, "3", false), GRASS(Color.GREEN, "4", true), BUILDING(Color.black, "5", true);

        private Color color;
        private String code;
        private String textureName;
        private BufferedImage bufferedImage;
        private boolean canMove;

        public BufferedImage getBufferedImage() {
            if (bufferedImage == null && textureName != null) {
                try {
                    bufferedImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(textureName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bufferedImage;
        }

        public void setBufferedImage(BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
        }

        CellType(Color color, String code, boolean canMove) {
            this.color = color;
            this.code = code;
            this.canMove = canMove;
        }

        CellType(Color color, String code, boolean canMove, String textureName) {
            this.color = color;
            this.code = code;
            this.textureName = textureName;
            this.canMove = canMove;
        }

        public boolean isCanMove() {
            return canMove;
        }

        public void setCanMove(boolean canMove) {
            this.canMove = canMove;
        }

        public String getTextureName() {
            return textureName;
        }

        public void setTextureName(String textureName) {
            this.textureName = textureName;
        }

        public void setColor(Color color) {
            this.color = color;
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

    public static void main(String[] args) {
        print(generateGround(generateSea(100), 17));

    }

    public static int getLenght() {
        return lenght;
    }

    public static void setLenght(int lenght) {
        Cell.lenght = lenght;
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
