package www.service.captchaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.service.captchaservice.model.*;
import www.service.captchaservice.service.ClientService;

@RestController
@RequestMapping("/${service.api.client:client}")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/${service.api.client.register:register}")
    public Client registerClient() {
        return clientService.registerClient();
    }

    @PostMapping(value = "/${service.api.client.verify:verify}")
    public Message verifyClient(@ModelAttribute Form form) throws Exception {
        Client client = clientService.findClientBySecretKey(form.getSecret());
        clientService.verifyClient(form.getToken(), client);
        return new SuccessMessage();
    }

}
