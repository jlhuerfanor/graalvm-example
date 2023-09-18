package org.example.sensemon.infrastructure;

import org.example.sensemon.infrastructure.settings.MonitoringSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;

@Configuration
public class SettingsConfiguration {
    private final ApplicationContext context;

    public SettingsConfiguration(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public MonitoringSettings monitoringSettings(
            @Value("${application.monitoring.source}") String source) throws IOException {
        var resource = context.getResource(source);
        var yaml = new Yaml(new Constructor(MonitoringSettings.class));

        try(var input = resource.getInputStream()) {
            return yaml.loadAs(input, MonitoringSettings.class);
        }
    }
}
