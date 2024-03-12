package com.example.serviceprovidertwo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author author
 * @since 2023-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@TableName("mq_message")
//@ApiModel(value = "MqMessage对象", description = "")
public class MqMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String msgId;

    private String msgContent;

    private String exchange;

    private String routeKey;

    private String queue;

    private Boolean publishAck;

    private Boolean consumeAck;

    private Boolean consumeFail;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String remark;


    public MqMessage() {

    }

    public MqMessage(String exchange, String routeKey, String queue, String msgContent) {
        this.msgId = UUID.randomUUID().toString();
        this.msgContent = msgContent;
        this.exchange = exchange;
        this.routeKey = routeKey;
        this.queue = queue;
        this.publishAck = false;
        this.consumeAck = false;
        this.consumeFail = false;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
