package www.service.captchaservice.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class GlobalPropertyHandler {

    public static final String TEST_MODE = "test";

    @Value("${service.mode}")
    private String mode;

    @Value("${service.ttl}")
    private int ttl;

    public String getMode() {
        return mode;
    }

    public int getTtl() {
        return ttl;
    }

}
