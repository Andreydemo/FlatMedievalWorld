package com.demosoft.testgame;

import com.demosoft.testgame.map.enity.Cell;
import com.demosoft.testgame.map.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
@Component
public class GameScene extends JPanel implements Initible {

    public static final int RADIUS = 12;
    public static final int CELL_PIXEL_WIDTH = 50;

    private CustomComponents customComponents;

    private TactGenerator.TactToken tactToken = TactGenerator.getTactToken();

    private MapComponent mapComponent;

    @Autowired
    private Player player;

    @Autowired
    private
    MapService mapService;

    @Autowired
    private GameWindow gameWindow;

    private KeyListenerWrapper keyListenerWrapper = new KeyListenerWrapper(new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println("JPanel keyTyped " + e.getKeyCode() + " " + KeyEvent.VK_UP);

        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Key pressed");
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    player.up();
                    break;
                case KeyEvent.VK_DOWN:
                    player.down();
                    break;
                case KeyEvent.VK_LEFT:
                    player.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    player.right();
                    break;
            }
            mapComponent.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("JPanel keyPressed " + e.getKeyCode());
        }
    });

    @PostConstruct
    public void init() {
        this.setLayout(null);

        this.addKeyListener(keyListenerWrapper);
        this.setVisible(true);
    }

    @Override
    public void initialize() {
        this.setFocusable(true);
        System.out.println("GameScene.requestFocusInWindow: " + this.requestFocusInWindow());
        CustomComponents customComponents = new CustomComponents();
        this.customComponents = customComponents;
        customComponents.setBounds(0, 0, Cell.lenght, Cell.lenght);
        customComponents.setLocation(gameWindow.getCenterPoint());

        mapComponent = new MapComponent(gameWindow.getSize());
        mapComponent.setBounds(0, 0, gameWindow.getSize().width, gameWindow.getSize().height);
        this.add(customComponents);
        this.add(mapComponent);
    }

    class CustomComponents extends JComponent {

        private static final long serialVersionUID = 1L;


        public CustomComponents() {
            this.setFocusable(true);
            System.out.println("CustomComponents.requestFocusInWindow: " + this.requestFocusInWindow());
            this.requestFocus();
            this.setLocation(gameWindow.getCenterPoint());

        }

        @Override
        public Dimension getMinimumSize() {
            return new Dimension(100, 100);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 300);
        }

        @Override
        public void paintComponent(Graphics g) {
            int margin = 0;
            Dimension dim = getSize();
            super.paintComponent(g);
            g.setColor(Color.green);
            //g.fillRect(margin, margin, dim.width, dim.height);
            try {
                final BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(player.getCurrentImage()));
                g.drawImage(image, 0, 0, this);
                g.finalize();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    class MapComponent extends JComponent {
        private Dimension dimension;

        public MapComponent(Dimension dimension) {
            this.dimension = dimension;
        }

        @Override
        public Dimension getMinimumSize() {
            return new Dimension(100, 100);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 300);
        }

        @Override
        public void paintComponent(Graphics g) {
            int margin = 0;
            Dimension dim = getSize();
            super.paintComponent(g);
            g.setColor(Color.green);
            // g.fillRect(margin, margin, 500, 500);
            Cell[][] cells = mapService.getCells(player.getPoint(), RADIUS);
            System.out.println("Rendering according to player position x:" + player.getPoint().getX() + " y:" + player.getPoint().getY());

            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    renderCell(g, cells[i][j], getPointOnWindow(i, j));
                }
            }
            g.finalize();
        }

        public Point getPointOnWindow(int x, int y) {
            Point point = gameWindow.getCenterPoint();
            return new Point((int) (point.getX() + ((x - RADIUS) * CELL_PIXEL_WIDTH)), (int) (point.getY() + ((y - RADIUS) * CELL_PIXEL_WIDTH)));
        }

        public void renderCell(Graphics g, Cell cell, Point awtPoint) {
            if (cell.getCellType().getTextureName() != null) {
                g.drawImage(cell.getCellType().getBufferedImage(), (int) awtPoint.getX(), (int) awtPoint.getY(), this);
                g.finalize();
            } else {
                g.setColor(cell.getCellType().getColor());
                g.fillRect((int) awtPoint.getX(), (int) awtPoint.getY(), CELL_PIXEL_WIDTH, CELL_PIXEL_WIDTH);
            }
        }
    }
}

