package whatsapp.com.whatsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Response {

    private String message_uuid;
//    String response = sb.toString();
//    ObjectMapper mapper = new ObjectMapper();
//    Response responseObject = mapper.readValue(response, Response.class);

    public Response() {

    }
}
