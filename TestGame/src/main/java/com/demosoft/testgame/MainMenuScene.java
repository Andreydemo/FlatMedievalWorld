package com.demosoft.testgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii_Korkoshko on 5/19/2016.
 */
@Component
public class MainMenuScene extends JPanel {

    private MainMenuScene currentInstance = this;

    @Autowired
    private GameScene gameScene;


    @Value("#{'${buttons}'.split(',')}")
    private List<String> buttons;

    @Value("#{PropertySplitter.map('${buttonsWidths}')}")
    private Map<String, Integer> buttonsWidths;

    @Value("#{PropertySplitter.map('${buttonsHeights}')}")
    private Map<String, Integer> buttonsHeights;

    @Value("#{PropertySplitter.map('${buttonsX}')}")
    private Map<String, Integer> buttonsX;

    @Value("#{PropertySplitter.map('${buttonsY}')}")
    private Map<String, Integer> buttonsY;

    private Map<String, JButton> buttonObjects = new HashMap<>();

    private Map<String, ActionListener> actionListenerMap = new HashMap<>();

    {
        actionListenerMap.put("start", e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(currentInstance);
            topFrame.setContentPane(gameScene);
            topFrame.setVisible(true);
        });
        actionListenerMap.put("exit", e -> {
            System.exit(0);
        });
    }

    @PostConstruct
    public void init() {
        this.setLayout(null);
        for (String buttonKey : buttons) {
            JButton newButton = new JButton(buttonKey);
            //newButton.setPreferredSize(new Dimension(buttonsWidths.get(buttonKey), buttonsHeights.get(buttonKey)));
            newButton.setSize(new Dimension(buttonsWidths.get(buttonKey), buttonsHeights.get(buttonKey)));
            newButton.setLocation(buttonsX.get(buttonKey), buttonsY.get(buttonKey));
            buttonObjects.put(buttonKey, newButton);
            newButton.addActionListener(actionListenerMap.get(buttonKey));
            this.add(newButton);
        }
        this.setVisible(true);
    }


}
