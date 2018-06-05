package www.service.captchaservice.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import www.service.captchaservice.dao.CaptchaDao;

@Component
public class DaoSchedule {

    @Autowired
    private CaptchaDao captchaDao;

    @Scheduled(fixedDelayString = "${service.schedule.delay}000")
    private void captchaDaoSchedule() {
        captchaDao.deleteIf(captcha -> captcha.isExpired());
    }

}
