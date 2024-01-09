package com.example.serviceprovidertwo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.serviceprovidertwo.model.entity.DemoProduct;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-12-26
 */
@Mapper
public interface DemoProductMapper extends BaseMapper<DemoProduct> {
    DemoProduct selectByPrimaryKey(Integer id);
}
