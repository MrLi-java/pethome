package top.lmqstudy.org.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
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

    /**
     * @Author Mr.Li
     * @Description 分页和高级高级查询
     * @Date 2021/1/13 9:18
     * @Param [query]
     * @return top.lmqstudy.basic.util.PageList<top.lmqstudy.org.domain.Shop>
     **/
    @PostMapping
    @ApiOperation(value = "分页查询和高级查询",notes = "需要前台页面传入分页的条件和高级查询的条件")
    public PageList<Shop> queryPage(@RequestBody ShopQuery query){
        return shopService.queryPage(query);
    }

    /**
     * @Author Mr.Li
     * @Description 系统平台员工审核
     * @Date 2021/1/13 9:18
     * @Param [id, type]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @GetMapping("/{id}/{type}/{auditMsg}")
    public AjaxResult shopAudit(@PathVariable("id")Long id,@PathVariable("type")Integer type,@PathVariable("auditMsg")String auditMsg){
        try {
            shopService.shopAudit(id,type,auditMsg);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("审核失败"+e.getMessage());
        }
    }

    /**
     * @Author Mr.Li
     * @Description 通过id查找shop
     * @Date 2021/1/13 16:57
     * @Param []
     * @return top.lmqstudy.org.domain.Shop
     **/
    @GetMapping("/{id}")
    public Shop getShopById(@PathVariable("id")Long id){
        return shopService.getById(id);
    }


}
