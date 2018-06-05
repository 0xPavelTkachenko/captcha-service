package www.service.captchaservice.model;

public abstract class Message {

    protected boolean success;

    protected String error;

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
