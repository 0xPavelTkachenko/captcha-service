package www.service.captchaservice.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import www.service.captchaservice.model.Token;

import java.io.IOException;

@JsonComponent
public class TokenSerializer extends JsonSerializer<Token> {

    @Override
    public void serialize(Token token, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("token", token.getValue());
        jsonGenerator.writeEndObject();
    }

}
