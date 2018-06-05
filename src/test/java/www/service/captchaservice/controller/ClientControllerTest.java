package www.service.captchaservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import www.service.captchaservice.model.Client;
import www.service.captchaservice.model.Form;
import www.service.captchaservice.model.Message;
import www.service.captchaservice.service.ClientService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @Test
    public void registerClient() {
        clientController.registerClient();
        verify(clientService, times(1)).registerClient();
    }

    @Test
    public void verifyClient() throws Exception {
        Form form = spy(new Form());
        doReturn("").when(form).getSecret();
        doReturn("").when(form).getToken();
        when(clientService.findClientBySecretKey(anyString())).thenReturn(new Client(null, null, null));
        doNothing().when(clientService).verifyClient(anyString(), any(Client.class));
        Message msg = clientController.verifyClient(form);
        assertNotNull(msg);
        verify(clientService, times(1)).verifyClient(anyString(), any(Client.class));
    }

}
