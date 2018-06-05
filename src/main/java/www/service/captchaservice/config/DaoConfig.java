package www.service.captchaservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import www.service.captchaservice.dao.*;

@Configuration
public class DaoConfig {

    @Bean
    public ClientDao clientDao() {
        return new ClientDaoImpl();
    }

    @Bean
    public CaptchaDao captchaDao() {
        return new CaptchaDaoImpl();
    }

}
