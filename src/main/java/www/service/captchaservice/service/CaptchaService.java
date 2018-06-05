package www.service.captchaservice.service;

import www.service.captchaservice.dao.CaptchaNotFoundException;
import www.service.captchaservice.model.Captcha;
import www.service.captchaservice.model.Client;
import www.service.captchaservice.model.Token;

public interface CaptchaService {

    Captcha createCaptcha(Client owner);

    Captcha findCaptchaById(String id) throws CaptchaNotFoundException, CaptchaExpiredException;

    Token solveCaptcha(String answer, Captcha captcha) throws BadAnswerException, CaptchaExpiredException;

}
