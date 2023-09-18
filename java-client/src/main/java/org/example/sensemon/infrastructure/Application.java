package org.example.sensemon.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "org.example.sensemon.infrastructure",
        "org.example.sensemon.application",
        "org.example.sensemon.adapter"
})
@ConfigurationPropertiesScan("org.example.sensemon.infrastructure.settings")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
