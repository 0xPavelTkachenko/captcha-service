package www.service.captchaservice.model;

public class ErrorMessage extends Message {

    public ErrorMessage(String error) {
        this.success = false;
        this.error = error;
    }

}
