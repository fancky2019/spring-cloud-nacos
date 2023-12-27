package com.example.serviceprovidertwo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.serviceprovidertwo.model.entity.DemoProduct;
import com.example.serviceprovidertwo.model.request.DemoProductRequest;
import org.fancky.model.response.PageData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-12-26
 */
public interface IDemoProductService extends IService<DemoProduct> {
    PageData<DemoProduct> getDemoProductList(DemoProductRequest request);
   void addDemoProductList(DemoProductRequest request);
}
