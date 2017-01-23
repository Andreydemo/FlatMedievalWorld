package com.demosoft.flatworld;

import com.demosoft.flatworld.graphic.GraphicApplicationContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Andrii_Korkoshko on 5/19/2016.
 */
public class Launcher {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
    }
}
