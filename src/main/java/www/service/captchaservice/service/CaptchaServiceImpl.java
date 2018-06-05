package www.service.captchaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import www.service.captchaservice.global.GlobalPropertyHandler;
import www.service.captchaservice.dao.CaptchaDao;
import www.service.captchaservice.dao.CaptchaNotFoundException;
import www.service.captchaservice.model.Captcha;
import www.service.captchaservice.model.Client;
import www.service.captchaservice.model.Token;
import www.service.captchaservice.util.CaptchaFactory;

import java.util.Date;
import java.util.UUID;

public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaDao captchaDao;

    @Autowired
    private CaptchaFactory captchaFactory;

    @Autowired
    private GlobalPropertyHandler propertyHandler;

    @Override
    public Captcha createCaptcha(Client owner) {
        String id = null;
        while ((id == null) || captchaDao.exists(id)) {
            id = UUID.randomUUID().toString().substring(0, 8);
        }

        Captcha captcha = captchaFactory.newCaptcha();
        captcha.setId(id);
        captcha.setOwner(owner);
        captcha.setExpiredDate(new Date(System.currentTimeMillis() + propertyHandler.getTtl() * 1000));
        captchaDao.add(captcha);
        return captcha;
    }

    @Override
    public Captcha findCaptchaById(String id) throws CaptchaNotFoundException, CaptchaExpiredException {
        Captcha captcha = captchaDao.find(id);
        if (captcha.isExpired()) {
            throw new CaptchaExpiredException();
        }
        return captcha;
    }

    @Override
    public Token solveCaptcha(String answer, Captcha captcha) throws BadAnswerException, CaptchaExpiredException {
        if (captcha.isExpired()) {
            throw new CaptchaExpiredException();
        }
        if ((captcha.getValue() == null) || (!captcha.getValue().equals(answer))) {
            throw new BadAnswerException();
        }
        captchaDao.delete(captcha.getId());
        String value = UUID.randomUUID().toString().substring(0, 8);
        return new Token(value);
    }

}
