package com.example.serviceprovidertwo.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("demo_product")
@Schema(name="DemoProduct对象 实体中文名称")
public class DemoProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
