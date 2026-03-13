package dev.sirenltd;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {
    private static final String PROPERTIES_FILE = "/ui-test.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream in = TestConfig.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (in == null) {
                throw new IllegalStateException("Missing required test configuration file: " + PROPERTIES_FILE);
            }
            PROPERTIES.load(in);
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to load test configuration file: " + PROPERTIES_FILE, ex);
        }
    }

    private TestConfig() {
    }

    public static String getBaseUrl() {
        String baseUrl = PROPERTIES.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("Missing required property 'baseUrl' in " + PROPERTIES_FILE);
        }
        return baseUrl;
    }
}
