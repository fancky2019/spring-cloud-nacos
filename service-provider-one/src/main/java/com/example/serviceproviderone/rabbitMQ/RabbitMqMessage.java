package com.example.serviceproviderone.rabbitMQ;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author ruili
 */
public class RabbitMqMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private String messageId;

    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime messageTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    /**
     * 要添加无参构造函数不然jackson 反序列报错
     */
    public RabbitMqMessage(){
    }

    public RabbitMqMessage(String content){
        this.content = content;
       // this.messageId = IdUtil.randomUUID();
        this.messageTime = LocalDateTime.now();
    }

}
