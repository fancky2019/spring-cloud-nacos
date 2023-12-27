package com.example.serviceprovidertwo.model.request;

import lombok.Data;
import org.fancky.model.request.Page;

import java.time.LocalDateTime;

@Data
public class DemoProductRequest extends Page {
    private Integer id;

    private String guid;

    private String productName;

    private String productStyle;

    private String imagePath;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer status;

    private String description;

    private LocalDateTime timestamp;
}
