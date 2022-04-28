package whatsapp.com.whatsapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import whatsapp.com.whatsapp.config.NexmoConfig;
import whatsapp.com.whatsapp.entity.Entity;


@Service
public class WhatsappServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(WhatsappServiceImpl.class);


    @Autowired
    private RestTemplate restTemplate;
    private final Entity entity;
    @Autowired
    private NexmoConfig nexmoConfig;

    public WhatsappServiceImpl() {
        entity = new Entity();
        nexmoConfig = new NexmoConfig();
    }


    public Entity addUser(Entity user) {
        String vonageUrl = "https://messages-sandbox.nexmo.com/v1/messages";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Request in string :: {}", user);
//        String apiKey = "6eba22d5"+":"+"QmXBt61Pq3klggFI";
        String auth = nexmoConfig.getApiKey()+":"+nexmoConfig.getApiSecret();
        HttpEntity<Entity> httpEntity = new HttpEntity<Entity>(user, httpHeaders);
        logger.info("Request in Http Entity :: {}", httpEntity);
        logger.info("Request in Http Entity :: {}");
//        httpHeaders.setBasicAuth("6eba22d5","QmXBt61Pq3klggFI");
        httpHeaders.setBasicAuth(nexmoConfig.getApiKey(),nexmoConfig.getApiSecret());


//        ResponseEntity<String> response=restTemplate.exchange(vonageUrl,HttpMethod.POST, httpEntity,String.class);
        try {
//            List<Entity> user = users.stream().map(user1 -> Entity
//                            .builder()
//                            .to(user1.getTo())
//                            .from(user1.getFrom())
//                            .channel(user1.getChannel())
//                            .message_type(user1.getMessage_type())
//                            .channel(user1.getChannel())
//                            .text(user1.getText())
//                            .build())
//                    .collect(Collectors.toList());


//        ResponseEntity<Void> res = restTemplate.postForEntity(vonageUrl,user, Void.class);
//            ResponseEntity<Entity> res = restTemplate.postForEntity(vonageUrl, user, Entity.class);
//            ResponseEntity<String> res = restTemplate.postForEntity(vonageUrl, user, String.class);
            ResponseEntity<Entity> res = restTemplate.postForEntity(vonageUrl, httpEntity, Entity.class);

//            if (res.getStatusCode() == HttpStatus.OK) {
//                return user;
//            }
            System.out.println(user);
            return user;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            throw new RuntimeException("Authorization exception");
        }
return null;

    }


}


