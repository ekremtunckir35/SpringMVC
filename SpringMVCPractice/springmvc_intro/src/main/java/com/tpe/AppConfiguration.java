package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
@ComponentScan("com.tpe") // default com.tpe
public class AppConfiguration {

    @Bean //bean metotlarin uzerinde kullanirim
    public Point point() {
        return new Point(56,45);
    }

}

//