package www.service.captchaservice.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import www.service.captchaservice.model.Client;

import java.io.IOException;

@JsonComponent
public class ClientSerializer extends JsonSerializer<Client> {

    @Override
    public void serialize(Client client, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("secret", client.getSecretKey());
        jsonGenerator.writeStringField("public", client.getPublicKey());
        jsonGenerator.writeEndObject();
    }

}
