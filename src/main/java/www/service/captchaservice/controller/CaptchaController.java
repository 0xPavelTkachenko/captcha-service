package www.service.captchaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import www.service.captchaservice.model.*;
import www.service.captchaservice.service.*;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/${service.api.captcha:captcha}")
public class CaptchaController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CaptchaService captchaService;

    @PostMapping(value = "/${service.api.captcha.create:create}")
    public Captcha createCaptcha(@ModelAttribute Form form) throws Exception {
        Client client = clientService.findClientByPublicKey(form.getPublic());
        return captchaService.createCaptcha(client);
    }

    @GetMapping(value = "/${service.api.captcha.image:image}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getCaptchaImage(@ModelAttribute Form form) throws Exception {
        Client client = clientService.findClientByPublicKey(form.getPublic());
        Captcha captcha = captchaService.findCaptchaById(form.getCaptcha());
        if (captcha.getOwner() != client) {
            throw new AccessDeniedException();
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(captcha.getImage(), "png", os);
        return os.toByteArray();
    }

    @PostMapping(value = "/${service.api.captcha.solve:solve}")
    public Token solveCaptcha(@ModelAttribute Form form) throws Exception {
        Client client = clientService.findClientByPublicKey(form.getPublic());
        Captcha captcha = captchaService.findCaptchaById(form.getCaptcha());
        if (captcha.getOwner() != client) {
            throw new AccessDeniedException();
        }
        Token token = captchaService.solveCaptcha(form.getAnswer(), captcha);
        client.setToken(token.getValue());
        return token;
    }

}
