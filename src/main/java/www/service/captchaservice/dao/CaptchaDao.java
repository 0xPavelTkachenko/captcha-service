package www.service.captchaservice.dao;

import www.service.captchaservice.model.Captcha;

import java.util.function.Predicate;

public interface CaptchaDao {

    void add(Captcha captcha);

    Captcha find(String id) throws CaptchaNotFoundException;

    void delete(String id);

    void deleteIf(Predicate<Captcha> filter);

    boolean exists(String id);

}
