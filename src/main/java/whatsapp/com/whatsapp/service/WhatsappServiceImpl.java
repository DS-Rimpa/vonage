package whatsapp.com.whatsapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import whatsapp.com.whatsapp.config.NexmoConfig;
import whatsapp.com.whatsapp.entity.Entity;
import whatsapp.com.whatsapp.entity.Response;

import java.net.URI;
import java.net.http.HttpClient;


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
    Gson gson = new Gson();
    HttpClient client= HttpClient.newHttpClient();
//    Response response = gson.fromJson(json, Response.class);
    Response response=new Response();


    public Response addUser(Entity user) {
        try {
        String vonageUrl = "https://messages-sandbox.nexmo.com/v1/messages";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Response nexmoResponse=null;
        logger.info("Request in string :: {}", user);
//        String apiKey = "6eba22d5"+":"+"QmXBt61Pq3klggFI";
        String auth = nexmoConfig.getApiKey()+":"+nexmoConfig.getApiSecret();
        HttpEntity<Entity> httpEntity = new HttpEntity<Entity>(user, httpHeaders);
        logger.info("Request in Http Entity :: {}", httpEntity);
//        httpHeaders.setBasicAuth("6eba22d5","QmXBt61Pq3klggFI");
        httpHeaders.setBasicAuth(nexmoConfig.getApiKey(),nexmoConfig.getApiSecret());
        URI uri=new URI(vonageUrl);
        String nexmoURI= UriComponentsBuilder.fromUri(uri).build().toUriString();
        ResponseEntity<String> responseEntity=restTemplate
                .exchange(nexmoURI,HttpMethod.POST,httpEntity,String.class);
            logger.info("Request in Http Entity :: {}", responseEntity.getBody());
            if(responseEntity.getBody().contains("message_uuid")){
                nexmoResponse=new ObjectMapper().readValue(responseEntity.getBody(),Response.class);
                response.setMessage_uuid(nexmoResponse.getMessage_uuid());

            }



//        ResponseEntity<String> response=restTemplate.exchange(vonageUrl,HttpMethod.POST, httpEntity,String.class);

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
            ObjectMapper objectMapper= new ObjectMapper();

            System.out.println(user);
//            Response response = gson.fromJson(user.getMessage_uuid(), Response.class);
//             response = client.send(user, new JsonBodyHandler<>(Response.class));


            return response;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            throw new RuntimeException("Authorization exception");
        }
        return null;

    }


}


