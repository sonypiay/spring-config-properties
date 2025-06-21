package programmer_zaman_now.spring.config.springbootmessagesource;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@SpringBootTest(classes = SpringBootMessageSourceTest.TestApplication.class)
public class SpringBootMessageSourceTest {

    @Autowired
    private TestApplication.SampleResource sampleResource;

    @Test
    void testHelloSony() {
        Assertions.assertEquals("Hello Sony", sampleResource.getHello(Locale.ENGLISH));
        Assertions.assertEquals("Halo Sony", sampleResource.getHello(new Locale("in_ID")));
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        public static class SampleResource implements MessageSourceAware {

            @Setter
            private MessageSource messageSource;

            public String getHello(Locale locale) {
                return messageSource.getMessage("hello", new Object[]{"Sony"}, locale);
            }
        }
    }
}
