package www.service.captchaservice.dao;

import www.service.captchaservice.model.Client;

public interface ClientDao {

    void add(Client client);

    Client find(String secretKey, String publicKey) throws ClientNotFoundException;

    boolean exists(String secretKey, String publicKey);

}
