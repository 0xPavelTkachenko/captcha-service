package www.service.captchaservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import www.service.captchaservice.util.*;

@Configuration
public class UtilConfig {

    @Bean
    public CaptchaFactory captchaFactory() {
        return new CaptchaFactoryImpl();
    }

}
