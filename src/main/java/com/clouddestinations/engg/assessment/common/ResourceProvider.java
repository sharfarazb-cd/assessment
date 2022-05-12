package com.clouddestinations.engg.assessment.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "app.entity")
@PropertySource(value = "classpath:config-prop.yml", factory = YmlPropertySourceFactory.class)
public class ResourceProvider {
    private Map<String, Object> rating = new HashMap<>();
    private Map<String, Object> variables = new HashMap<>();

    public Map<String, Object> getRating() {
        return rating;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }
}
