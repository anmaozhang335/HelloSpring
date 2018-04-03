package com.zam.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private String[] encryptProNames = { "jdbc.username", "jdbc.password" };

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProperty(propertyName)) {
            return DESUtil.getDecryptString(propertyValue);
        } else {
            return propertyValue;
        }
    }

    private boolean isEncryptProperty(String name) {
        for (String propertyName : encryptProNames) {
            if (propertyName.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
