package www.service.captchaservice.model;

import java.awt.image.RenderedImage;
import java.util.Date;

public class Captcha {

    private String id;

    private String value;

    private RenderedImage image;

    private Client owner;

    private Date expiredDate;

    public Captcha(String id, String value, RenderedImage image, Client owner, Date expiredDate) {
        this.id = id;
        this.value = value;
        this.image = image;
        this.owner = owner;
        this.expiredDate = expiredDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RenderedImage getImage() {
        return image;
    }

    public void setImage(RenderedImage image) {
        this.image = image;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= expiredDate.getTime();
    }

}
