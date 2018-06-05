package www.service.captchaservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import www.service.captchaservice.dao.ClientDao;
import www.service.captchaservice.dao.ClientNotFoundException;
import www.service.captchaservice.model.Client;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @InjectMocks
    private ClientService clientService = new ClientServiceImpl();

    @Mock
    private ClientDao clientDao;

    @Test
    public void registerClient() {
        Client client = clientService.registerClient();
        verify(clientDao, times(1)).add(any(Client.class));
        assertNotNull(client.getSecretKey());
        assertNotNull(client.getPublicKey());;
        assertTrue(client.getPublicKey().length() > 0);
        assertTrue(client.getSecretKey().length() > 0);
        assertNull(client.getToken());
    }

    @Test
    public void findClientBySecretKey() throws Exception {
        Client client1 = new Client(null, null, null);
        when(clientDao.find(anyString(), eq(null))).thenReturn(client1);
        Client client2 = clientService.findClientBySecretKey("");
        verify(clientDao, times(1)).find(anyString(), eq(null));
        assertEquals(client1, client2);
    }

    @Test(expected = ClientNotFoundException.class)
    public void findClientBySecretKeyClientNotFoundException() throws Exception {
        when(clientDao.find(anyString(), eq(null))).thenThrow(ClientNotFoundException.class);
        clientService.findClientBySecretKey("");
    }

    @Test
    public void findClientByPublicKey() throws Exception {
        Client client1 = new Client(null, null, null);
        when(clientDao.find(eq(null), anyString())).thenReturn(client1);
        Client client2 = clientService.findClientByPublicKey("");
        verify(clientDao, times(1)).find(eq(null), anyString());
        assertEquals(client1, client2);
    }

    @Test(expected = ClientNotFoundException.class)
    public void findClientByPublicKeyClientNotFoundException() throws Exception {
        when(clientDao.find(eq(null), anyString())).thenThrow(ClientNotFoundException.class);
        clientService.findClientByPublicKey("");
    }

    @Test
    public void verifyClient() throws Exception {
        Client client = new Client(null, null, "token");
        clientService.verifyClient("token", client);
        assertNull(client.getToken());
    }

    @Test(expected = BadTokenException.class)
    public void verifyClientBadTokenException() throws Exception {
        clientService.verifyClient("token", new Client(null, null, "token2"));
    }

}
