package www.service.captchaservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import www.service.captchaservice.model.Captcha;
import www.service.captchaservice.model.Client;
import www.service.captchaservice.model.Form;
import www.service.captchaservice.service.CaptchaService;
import www.service.captchaservice.service.ClientService;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CaptchaControllerTest {

    @InjectMocks
    private CaptchaController captchaController;

    @Mock
    private ClientService clientService;

    @Mock
    private CaptchaService captchaService;

    @Test
    public void createCaptcha() throws Exception {
        Form form = spy(new Form());
        doReturn("").when(form).getPublic();
        when(clientService.findClientByPublicKey(anyString())).thenReturn(new Client(null, null, null));
        captchaController.createCaptcha(form);
        verify(captchaService, times(1)).createCaptcha(any(Client.class));
    }

    @Test(expected = AccessDeniedException.class)
    public void getCaptchaImageAccessDeniedException() throws Exception {
        Form form = spy(new Form());
        doReturn("").when(form).getPublic();
        doReturn("").when(form).getCaptcha();
        Client client = new Client(null, null, null);
        Captcha captcha = new Captcha(null, null, null, null, null);
        when(clientService.findClientByPublicKey(anyString())).thenReturn(client);
        when(captchaService.findCaptchaById(anyString())).thenReturn(captcha);
        captchaController.getCaptchaImage(form);
    }

    @Test(expected = AccessDeniedException.class)
    public void solveCaptchaAccessDeniedException() throws Exception {
        Form form = spy(new Form());
        doReturn("").when(form).getPublic();
        doReturn("").when(form).getCaptcha();
        Client client = new Client(null, null, null);
        Captcha captcha = new Captcha(null, null, null, null, null);
        when(clientService.findClientByPublicKey(anyString())).thenReturn(client);
        when(captchaService.findCaptchaById(anyString())).thenReturn(captcha);
        captchaController.solveCaptcha(form);
    }

}
