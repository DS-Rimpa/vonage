package whatsapp.com.whatsapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import whatsapp.com.whatsapp.entity.Entity;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WhatsappServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(WhatsappServiceImpl.class);


    @Autowired
    private RestTemplate restTemplate;
    private final Entity entity;

    public WhatsappServiceImpl() {
        entity = new Entity();
    }


    public List<Entity> addUser(List<Entity> users) {
        String vonageUrl = "https://messages-sandbox.nexmo.com/v1/messages";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Request in string :: {}", users);


//        ResponseEntity<String> response=restTemplate.exchange(vonageUrl,HttpMethod.POST, httpEntity,String.class);
        try {
            List<Entity> user = users.stream().map(user1 -> Entity
                            .builder()
                            .to(user1.getTo())
                            .from(user1.getFrom())
                            .channel(user1.getChannel())
                            .message_type(user1.getMessage_type())
                            .channel(user1.getChannel())
                            .text(user1.getText())
                            .build())
                    .collect(Collectors.toList());


            HttpEntity<Entity> httpEntity = new HttpEntity<Entity>(user.get(0), httpHeaders);
            logger.info("Request in Http Entity :: {}", httpEntity);
            logger.info("Request in Http Entity :: {}");

//        ResponseEntity<Void> res = restTemplate.postForEntity(vonageUrl,user, Void.class);
//            ResponseEntity<Entity> res = restTemplate.postForEntity(vonageUrl, user, Entity.class);
//            ResponseEntity<String> res = restTemplate.postForEntity(vonageUrl, user, String.class);
            ResponseEntity<String> res = restTemplate.exchange(vonageUrl, HttpMethod.POST, httpEntity, String.class);

            if (res.getStatusCode() == HttpStatus.OK) {
                return users;
            }
            System.out.println(users);
            return users;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException("Authorization exception");
        }


    }

    public Entity getEntity(Entity entity1)
    {

         entity1.setTo(entity.getTo());
         return entity1;
    }
}


