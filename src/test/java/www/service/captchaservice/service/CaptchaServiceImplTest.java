package www.service.captchaservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import www.service.captchaservice.dao.CaptchaDao;
import www.service.captchaservice.dao.CaptchaNotFoundException;
import www.service.captchaservice.global.GlobalPropertyHandler;
import www.service.captchaservice.model.Captcha;
import www.service.captchaservice.model.Client;
import www.service.captchaservice.model.Token;
import www.service.captchaservice.util.CaptchaFactory;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CaptchaServiceImplTest {

    @InjectMocks
    private CaptchaService captchaService = new CaptchaServiceImpl();

    @Mock
    private CaptchaDao captchaDao;

    @Mock
    private CaptchaFactory captchaFactory;

    @Mock
    private GlobalPropertyHandler propertyHandler;

    @Test
    public void createCaptcha() {
        Client owner = new Client(null, null, null);
        when(captchaFactory.newCaptcha()).thenReturn(new Captcha(null, null, null, null, null));
        Captcha captcha = captchaService.createCaptcha(owner);
        assertNotNull(captcha.getId());
        assertTrue(captcha.getId().length() > 0);
        assertEquals(owner, captcha.getOwner());
        assertNotNull(captcha.getExpiredDate());
        verify(captchaDao, times(1)).add(captcha);
    }

    @Test
    public void findCaptchaById() throws Exception {
        Captcha expectedCaptcha = spy(new Captcha(null, null, null, null, null));
        doReturn(false).when(expectedCaptcha).isExpired();
        when(captchaDao.find(anyString())).thenReturn(expectedCaptcha);
        Captcha actualCaptcha = captchaService.findCaptchaById(anyString());
        assertEquals(expectedCaptcha, actualCaptcha);
    }

    @Test(expected = CaptchaNotFoundException.class)
    public void findCaptchaByIdCaptchaNotFoundException() throws Exception {
        when(captchaDao.find(anyString())).thenThrow(CaptchaNotFoundException.class);
        captchaService.findCaptchaById(anyString());
    }

    @Test(expected = CaptchaExpiredException.class)
    public void findCaptchaByIdCaptchaExpiredException() throws Exception {
        Captcha captcha = spy(new Captcha(null, null, null, null, null));
        doReturn(true).when(captcha).isExpired();
        when(captchaDao.find(anyString())).thenReturn(captcha);
        captchaService.findCaptchaById(anyString());
    }

    @Test
    public void solveCaptcha() throws Exception {
        Captcha captcha = spy(new Captcha(null, "answer", null, null, null));
        doReturn(false).when(captcha).isExpired();
        Token token = captchaService.solveCaptcha("answer", captcha);
        assertNotNull(token);
        assertNotNull(token.getValue());
        assertTrue(token.getValue().length() > 0);
    }

    @Test(expected = BadAnswerException.class)
    public void solveCapthaBadAnswerException() throws Exception {
        Captcha captcha = spy(new Captcha(null, "answer", null, null, null));
        doReturn(false).when(captcha).isExpired();
        captchaService.solveCaptcha("bad answer", captcha);
    }

    @Test(expected = CaptchaExpiredException.class)
    public void solveCapthaCaptchaExpiredException() throws Exception {
        Captcha captcha = spy(new Captcha(null, null, null, null, null));
        doReturn(true).when(captcha).isExpired();
        captchaService.solveCaptcha(null, captcha);
    }

}
