package www.service.captchaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import www.service.captchaservice.dao.ClientDao;
import www.service.captchaservice.dao.ClientNotFoundException;
import www.service.captchaservice.model.Client;

import java.util.UUID;

public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client registerClient() {
        String secretKey = null;
        while ((secretKey == null) || (clientDao.exists(secretKey, null))) {
            secretKey = UUID.randomUUID().toString();
        }

        String publicKey = null;
        while ((publicKey == null) || (clientDao.exists(null, publicKey))) {
            publicKey = UUID.randomUUID().toString();
        }

        Client client = new Client(publicKey, secretKey, null);
        clientDao.add(client);
        return client;
    }

    @Override
    public Client findClientBySecretKey(String secretKey) throws ClientNotFoundException {
        return clientDao.find(secretKey, null);
    }

    @Override
    public Client findClientByPublicKey(String publicKey) throws ClientNotFoundException {
        return clientDao.find(null, publicKey);
    }

    @Override
    public void verifyClient(String token, Client client) throws BadTokenException {
        if ((client.getToken() == null) || (!client.getToken().equals(token))) {
            throw new BadTokenException();
        }
        client.setToken(null);
    }

}
