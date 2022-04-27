package whatsapp.com.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import whatsapp.com.whatsapp.entity.Entity;
import whatsapp.com.whatsapp.service.WhatsappServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class Controller {


    private WhatsappServiceImpl whatsappService;

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Entity saveUsers(@RequestBody Entity users){
        return whatsappService.addUser(users);
    }

    @Autowired
    public void setWhatsappService(WhatsappServiceImpl whatsappService) {
        this.whatsappService = whatsappService;
    }
}
