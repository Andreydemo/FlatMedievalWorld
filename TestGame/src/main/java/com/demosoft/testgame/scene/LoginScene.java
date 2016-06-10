package com.demosoft.testgame.scene;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii_Korkoshko on 6/10/2016.
 */
@Component
public class LoginScene extends JPanel {
    private LoginScene currentInstance = this;

    @Autowired
    private GameScene gameScene;


    @Value("#{'${login.buttons}'.split(',')}")
    private List<String> buttons;



    @Value("#{PropertySplitter.map('${login.buttonsWidths}')}")
    private Map<String, Integer> buttonsWidths;

    @Value("#{PropertySplitter.map('${login.buttonsHeights}')}")
    private Map<String, Integer> buttonsHeights;

    @Value("#{PropertySplitter.map('${login.buttonsX}')}")
    private Map<String, Integer> buttonsX;

    @Value("#{PropertySplitter.map('${login.buttonsY}')}")
    private Map<String, Integer> buttonsY;


    @Value("#{'${login.inputs}'.split(',')}")
    private List<String> inputs;



    @Value("#{PropertySplitter.map('${login.inputsWidths}')}")
    private Map<String, Integer> inputsWidths;

    @Value("#{PropertySplitter.map('${login.inputsHeights}')}")
    private Map<String, Integer> inputsHeights;

    @Value("#{PropertySplitter.map('${login.inputsX}')}")
    private Map<String, Integer> inputsX;

    @Value("#{PropertySplitter.map('${login.inputsY}')}")
    private Map<String, Integer> inputsY;

    private Map<String, JButton> buttonObjects = new HashMap<>();

    private Map<String, JTextField> inputsObjects = new HashMap<>();

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

        for(String inputKey: inputs){
            JTextField jTextField = new JFormattedTextField();
            jTextField.setSize(inputsWidths.get(inputKey), inputsHeights.get(inputKey));
            jTextField.setLocation(inputsX.get(inputKey),inputsY.get(inputKey));
            inputsObjects.put(inputKey,jTextField);
            this.add(jTextField);
        }
        this.setVisible(true);
    }

}
