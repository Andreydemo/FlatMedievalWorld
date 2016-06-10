package com.demosoft.testgame;

import com.demosoft.testgame.scene.LoginScene;
import com.demosoft.testgame.scene.MainMenuScene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Andrii_Korkoshko on 5/19/2016.
 */
@Component
public class GameWindow extends JFrame {

    @Value("${height}")
    private int height;
    @Value("${width}")
    private int width;

    @Value("${x}")
    private int x;
    @Value("${y}")
    private int y;

    @Autowired
    private MainMenuScene mainMenuScene;
    @Autowired
    private LoginScene loginScene;


    @PostConstruct
    public void init() {
        // frame = new JFrame("FrameDemo");

        //2. Optional: What happens when the frame closes?
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //3. Create components and put them in the frame.
        //...create emptyLabel...
        //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        this.setBounds(x, y, width, height);
        this.setContentPane(loginScene);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        //4. Size the frame.
        // frame.pack();

        //5. Show it.
        this.setVisible(true);
    }

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
        if (contentPane instanceof Initible) {
            ((Initible) contentPane).initialize();
        }
    }

    public Point getCenterPoint(){
        Dimension dimension =  getSize();
        return new Point(dimension.width/2,dimension.height/2);
    }
}
