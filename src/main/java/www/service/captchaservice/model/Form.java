package www.service.captchaservice.model;

public class Form {

    private String publicParam;

    private String secretParam;

    private String captchaParam;

    private String answerParam;

    private String tokenParam;

    public String getPublic() throws ParameterNotFoundException {
        if (publicParam == null) {
            throw new ParameterNotFoundException("public");
        }
        return publicParam;
    }

    public void setPublic(String publicParam) {
        this.publicParam = publicParam;
    }

    public String getSecret() throws ParameterNotFoundException {
        if (secretParam == null) {
            throw new ParameterNotFoundException("secret");
        }
        return secretParam;
    }

    public void setSecret(String secretParam) {
        this.secretParam = secretParam;
    }

    public String getCaptcha() throws ParameterNotFoundException {
        if (captchaParam == null) {
            throw new ParameterNotFoundException("captcha");
        }
        return captchaParam;
    }

    public void setCaptcha(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    public String getAnswer() throws ParameterNotFoundException {
        if (answerParam == null) {
            throw new ParameterNotFoundException("answer");
        }
        return answerParam;
    }

    public void setAnswer(String answerParam) {
        this.answerParam = answerParam;
    }

    public String getToken() throws ParameterNotFoundException {
        if (tokenParam == null) {
            throw new ParameterNotFoundException("token");
        }
        return tokenParam;
    }

    public void setToken(String tokenParam) {
        this.tokenParam = tokenParam;
    }

}
