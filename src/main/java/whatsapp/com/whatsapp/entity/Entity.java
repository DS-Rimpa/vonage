package whatsapp.com.whatsapp.entity;


import lombok.*;

import java.util.List;

@ToString
@Builder
@Data
@NoArgsConstructor
public class Entity {


    public Entity(String message_type, String to, String from, String channel, String message_uuid, String text) {
        this.message_type = message_type;
        this.to = to;
        this.from = from;
        this.channel = channel;
        this.message_uuid = message_uuid;
        this.text = text;
    }



    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String setFrom(String from) {
        this.from = from;
        return from;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessage_uuid() {
        return message_uuid;
    }

    public void setMessage_uuid(String message_uuid) {
        this.message_uuid = message_uuid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String message_type;
    private String to;
    private String from;
    private String channel;
    private String message_uuid;
    private String text;


}