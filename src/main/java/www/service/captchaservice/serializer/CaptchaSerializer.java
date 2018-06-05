package www.service.captchaservice.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import www.service.captchaservice.global.GlobalPropertyHandler;
import www.service.captchaservice.model.Captcha;

import java.io.IOException;

@JsonComponent
public class CaptchaSerializer extends JsonSerializer<Captcha> {

    @Autowired
    private GlobalPropertyHandler propertyHandler;

    @Override
    public void serialize(Captcha captcha, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
    throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("captcha", captcha.getId());
        if (propertyHandler.getMode().equalsIgnoreCase(GlobalPropertyHandler.TEST_MODE)) {
            jsonGenerator.writeStringField("answer", captcha.getValue());
        }
        jsonGenerator.writeEndObject();
    }

}
