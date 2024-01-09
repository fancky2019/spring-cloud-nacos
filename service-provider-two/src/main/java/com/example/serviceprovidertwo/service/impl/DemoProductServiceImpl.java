package com.example.serviceprovidertwo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.serviceprovidertwo.mapper.DemoProductMapper;
import com.example.serviceprovidertwo.model.entity.DemoProduct;
import com.example.serviceprovidertwo.model.request.DemoProductRequest;
import com.example.serviceprovidertwo.service.IDemoProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.fancky.model.response.PageData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2023-12-26
 */
@Service
public class DemoProductServiceImpl extends ServiceImpl<DemoProductMapper, DemoProduct> implements IDemoProductService {
    public PageData<DemoProduct> getDemoProductList(DemoProductRequest request) {

        DemoProduct demoProduct=   this.getBaseMapper().selectByPrimaryKey(100);

        PageData<DemoProduct> pageData = new PageData<>();
        PageHelper.startPage(request.getPageIndex(), request.getPageSize());


        //拦截要执行的sql:先执行 select count(0),然后执行select 列
        //简单的sql  count(0) 能替换列，复杂的sql 直接包了一层sql 然后count(0)

        QueryWrapper<DemoProduct> queryWrapper = new QueryWrapper<DemoProduct>();
//        queryWrapper.select("","");
//        queryWrapper.eq("",1);
//        queryWrapper.ne();

        //有条件拼接条件
        queryWrapper.eq(StringUtils.isNotEmpty(request.getProductName()), "product_name", request.getProductName());
//        queryWrapper.orderByDesc("desc");
        List<DemoProduct> list = this.list(queryWrapper);


        //调用分页查询的方法
        PageInfo<DemoProduct> pageInfo = new PageInfo<>(list);
        pageData.setData(pageInfo.getList());
        pageData.setCount(pageInfo.getSize());

//            return  pageInfo;

        return pageData;
    }

    public void addDemoProductList(DemoProductRequest request) {

    }
}
