package com.example.serviceprovidertwo.controller;


import com.example.serviceprovidertwo.model.entity.DemoProduct;
import com.example.serviceprovidertwo.model.request.DemoProductRequest;
import com.example.serviceprovidertwo.service.IDemoProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.fancky.model.response.MessageResult;
import org.fancky.model.response.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2023-12-26
 */
@Tag(name = "DemoProduct")
@RestController
@RequestMapping("/api/demoProduct")
public class DemoProductController {
    @Autowired
    private IDemoProductService demoProductService;

    /**
     * DemoProduct 分页查询 fancky
     *
     * @param request
     * @return
     */
    @Operation(summary = "DemoProduct 分页查询", description = "获取分页列表的详细说明")
    @GetMapping("/getDemoProductList")
    public MessageResult<PageData<DemoProduct>> getDemoProductList(DemoProductRequest request) {
        return MessageResult.success(demoProductService.getDemoProductList(request));


    }

    @PostMapping("/addDemoProductList")
    public MessageResult<Void> addDemoProductList(@RequestBody DemoProductRequest request) {
        demoProductService.addDemoProductList(request);
        return MessageResult.success();

    }
}
