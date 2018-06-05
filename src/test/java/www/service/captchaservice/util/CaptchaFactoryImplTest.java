package www.service.captchaservice.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import www.service.captchaservice.model.Captcha;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CaptchaFactoryImplTest {

    private CaptchaFactory captchaFactory = new CaptchaFactoryImpl();

    @Test
    public void newCaptcha() {
        Captcha captcha = captchaFactory.newCaptcha();
        assertNull(captcha.getId());
        assertNull(captcha.getOwner());
        assertNull(captcha.getExpiredDate());
        assertNotNull(captcha.getValue());
        assertTrue(captcha.getValue().length() > 0);
        assertNotNull(captcha.getImage());
    }

}
