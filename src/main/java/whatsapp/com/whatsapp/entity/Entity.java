package whatsapp.com.whatsapp.entity;


import lombok.*;

import javax.validation.groups.ConvertGroup;


@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {

    private String message_type;
    private String to;
    private String from;
    private String channel;
    private String message_uuid;
    private String text;
}