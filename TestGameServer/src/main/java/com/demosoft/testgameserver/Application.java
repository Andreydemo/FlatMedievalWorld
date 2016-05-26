package com.demosoft.testgameserver; /**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */

import java.util.Arrays;

import com.demosoft.testgameserver.map.MapComponent;
import com.demosoft.testgameserver.map.MapUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("com.demosoft.testgameserver")
@ImportResource("springAppConfig.xml")
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        /*System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
        System.out.println("Server inited with map");

        MapComponent mapComponent = ctx.getBean(MapComponent.class);
        MapUtils.print(mapComponent.map);
    }

}