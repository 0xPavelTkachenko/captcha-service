package www.service.captchaservice.util;

import www.service.captchaservice.model.Captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaFactoryImpl implements CaptchaFactory {

    @Override
    public Captcha newCaptcha() {
        Random random = new Random();
        char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] data = new char[6];
        for (int i = 0; i < data.length; i++) {
            data[i] = alphabet[random.nextInt(alphabet.length)];
        }

        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        final int WIDTH = 240;
        final int HEIGHT = 80;

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Verdana", Font.BOLD, 40));
        graphics.setBackground(new Color(r, g, b));
        graphics.clearRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(new Color(255 - r, 255 - g, 255 - b));
        for (int i = 0; i < data.length; i++) {
            int x = 17 + i * 35 + random.nextInt(7);
            int y = 35 + random.nextInt(41);
            graphics.drawChars(data, i, 1, x, y);
        }
        graphics.dispose();

        return new Captcha(null, new String(data), image, null, null);
    }

}
