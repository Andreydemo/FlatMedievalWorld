package com.demosoft.flatworld.graphic;

import com.demosoft.flatworld.graphic.ui.menu.FlippedStage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Andrii_Korkoshko on 1/17/2017.
 */
@Configuration
public class GraphicConfig {

    @Bean
    GraphicApplicationContainer container(){
        return new GraphicApplicationContainer();
    }

    @Bean
    FlippedStage flippedStage(){
        return new FlippedStage();
    }
}
