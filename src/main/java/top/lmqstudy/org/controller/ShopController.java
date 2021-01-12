package top.lmqstudy.org.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.org.domain.Shop;
import top.lmqstudy.org.query.ShopQuery;
import top.lmqstudy.org.service.IShopService;


@RestController
@RequestMapping("/shop")
@Api(tags = "门店接口",description = "门店接口详细描述")
public class ShopController {
    @Autowired
    private IShopService shopService;

    @PostMapping
    @ApiOperation(value = "分页查询和高级查询",notes = "需要前台页面传入分页的条件和高级查询的条件")
    public PageList<Shop> queryPage(@RequestBody ShopQuery query){
        return shopService.queryPage(query);
    }


}
