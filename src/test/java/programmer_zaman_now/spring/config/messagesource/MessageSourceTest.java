package programmer_zaman_now.spring.config.messagesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class MessageSourceTest {

    private ApplicationContext context;
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(TestApplication.class);
        messageSource = context.getBean(MessageSource.class);
    }

    @Test
    void testDefaultLocale() {
        final String hello = messageSource.getMessage("hello", new Object[]{"Sony"}, Locale.ENGLISH);
        Assertions.assertEquals("Hello Sony", hello);
    }

    @Test
    void testIndonesianLocale() {
        final String hello = messageSource.getMessage("hello", new Object[]{"Sony"}, new Locale("in_ID"));
        Assertions.assertEquals("Halo Sony", hello);
    }

    @SpringBootApplication
    public static class TestApplication {

        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("my");
            return messageSource;
        }
    }
}
