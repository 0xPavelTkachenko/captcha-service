package www.service.captchaservice.service;

import www.service.captchaservice.dao.ClientNotFoundException;
import www.service.captchaservice.model.Client;

public interface ClientService {

    Client registerClient();

    Client findClientBySecretKey(String secretKey) throws ClientNotFoundException;

    Client findClientByPublicKey(String publicKey) throws ClientNotFoundException;

    void verifyClient(String token, Client client) throws BadTokenException;

}
