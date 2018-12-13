package com.electrolux.washmachine.localization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class Localization {

    private final Logger logger = LoggerFactory.getLogger(Localization.class);

    private ResourceBundle resourceBundle;

    private Localization() {
        logger.info("Load localization");
        resourceBundle = ResourceBundle.getBundle("localization.data", new UTF8Control());
    }

    public String getString(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (Exception ex) {
            logger.warn(String.format("Can't find string by key %s", key));
            return "";
        }
    }
}
