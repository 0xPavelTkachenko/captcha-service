package www.service.captchaservice.dao;

import www.service.captchaservice.model.Captcha;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public class CaptchaDaoImpl implements CaptchaDao {

    private Map<String, Captcha> captchas = new ConcurrentHashMap<>();

    @Override
    public void add(Captcha captcha) {
        captchas.put(captcha.getId(), captcha);
    }

    @Override
    public Captcha find(String id) throws CaptchaNotFoundException {
        Captcha captcha = captchas.get(id);
        if (captcha != null) {
            return captcha;
        } else {
            throw new CaptchaNotFoundException();
        }
    }

    @Override
    public void delete(String id) {
        captchas.remove(id);
    }

    @Override
    public void deleteIf(Predicate<Captcha> filter) {
        captchas.entrySet().removeIf(entry -> filter.test(entry.getValue()));
    }

    @Override
    public boolean exists(String id) {
        return captchas.containsKey(id);
    }

}
