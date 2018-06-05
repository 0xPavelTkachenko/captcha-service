package www.service.captchaservice.dao;

import www.service.captchaservice.model.Client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientDaoImpl implements ClientDao {

    private Map<String, Client> clientsBySecretKey = new ConcurrentHashMap<>();
    private Map<String, Client> clientsByPublicKey = new ConcurrentHashMap<>();

    @Override
    public void add(Client client) {
        clientsBySecretKey.put(client.getSecretKey(), client);
        clientsByPublicKey.put(client.getPublicKey(), client);
    }

    @Override
    public Client find(String secretKey, String publicKey) throws ClientNotFoundException {
        Client client = null;
        if (secretKey != null) {
            client = clientsBySecretKey.get(secretKey);
        } else if (publicKey != null) {
            client = clientsByPublicKey.get(publicKey);
        }
        if (client == null) {
            throw new ClientNotFoundException();
        }
        return client;
    }

    @Override
    public boolean exists(String secretKey, String publicKey) {
        if (secretKey != null) {
            return clientsBySecretKey.containsKey(secretKey);
        } else if (publicKey != null) {
            return clientsByPublicKey.containsKey(publicKey);
        }
        return false;
    }

}
