package www.service.captchaservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import www.service.captchaservice.service.*;

@Configuration
public class ServiceConfig {

    @Bean
    public ClientService clientService() {
        return new ClientServiceImpl();
    }

    @Bean
    public CaptchaService captchaService() {
        return new CaptchaServiceImpl();
    }

}
