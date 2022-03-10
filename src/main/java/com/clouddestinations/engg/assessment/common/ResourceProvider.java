package com.clouddestinations.engg.assessment.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "app.entity")
@PropertySource(value = "classpath:config-prop.yml", factory = YmlPropertySourceFactory.class)
public class ResourceProvider {
    private HashMap<String, Object> rating = new HashMap<>();
    private HashMap<String, Object> variables = new HashMap<>();

    public HashMap<String, Object> getRating() {
        return rating;
    }

    public HashMap<String, Object> getVariables() { return variables; }
}
