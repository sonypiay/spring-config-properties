package programmer_zaman_now.spring.config.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = ValueTest.TestApplication.class)
public class ValueTest {

    @Autowired
    private TestApplication.ApplicationProperties applicationProperties;

    @Autowired
    private TestApplication.SystemProperties sys;

    @Test
    void testValue() {
        final String name = applicationProperties.getName();
        final int version = applicationProperties.getVersion();
        final boolean productionMode = applicationProperties.isProductionMode();

        Assertions.assertEquals("Belajar Spring Boot", name);
        Assertions.assertEquals(1, version);
        Assertions.assertFalse(productionMode);
    }

    @Test
    void testSystemProperties() {
        Assertions.assertNotNull(sys.getJavaHome());
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        @Getter
        public static class ApplicationProperties {

            @Value("${application.name}")
            private String name;

            @Value("${application.version}")
            private int version;

            @Value("${application.production-mode}")
            private boolean productionMode;
        }

        @Component
        @Getter
        public static class SystemProperties {

            @Value("${JAVA_HOME}")
            private String javaHome;
        }
    }
}
