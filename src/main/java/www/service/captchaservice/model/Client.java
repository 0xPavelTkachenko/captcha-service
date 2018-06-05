package www.service.captchaservice.model;

public class Client {

    private String secretKey;

    private String publicKey;

    private String token;

    public Client(String secretKey, String publicKey, String token) {
        this.secretKey = secretKey;
        this.publicKey = publicKey;
        this.token = token;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
