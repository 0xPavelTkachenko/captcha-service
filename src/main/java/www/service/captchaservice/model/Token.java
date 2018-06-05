package www.service.captchaservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {

    @JsonProperty("token")
    private String value;

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
