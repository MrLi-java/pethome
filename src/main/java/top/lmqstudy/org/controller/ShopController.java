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

import java.util.List;

@RestController
@RequestMapping("/shop")
@Api(tags = "门店接口",description = "门店接口详细描述")
public class ShopController {
    @Autowired
    private IShopService shopService;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id查询门店",notes = "需要前台页面传入id")
    public Shop getById(@PathVariable("id") Long id){
        return shopService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "门店添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResult addOrUpdate(@RequestBody Shop shop){
        try {
            if (shop.getId() == null) {
                shopService.add(shop);
            }else {
                shopService.update(shop);
            }
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setMsg("系统繁忙！！"+e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除门店",notes = "需要前台页面传入id")
    public AjaxResult deleteById(@PathVariable Long id){
        try {
            shopService.del(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("系统繁忙！！"+e.getMessage());
        }
    }

    @GetMapping
    @ApiOperation(value = "查询所有门店",notes = "不需要任何参数")
    public List<Shop> getAll(){
        return shopService.getAll();
    }

    @PostMapping
    @ApiOperation(value = "分页查询和高级查询",notes = "需要前台页面传入分页的条件和高级查询的条件")
    public PageList<Shop> queryPage(@RequestBody ShopQuery query){
        return shopService.queryPage(query);
    }

    @PatchMapping
    @ApiOperation(value = "批量删除",notes = "前端传入要删除门店id的集合")
    public AjaxResult patchDelete(@RequestBody List<Long> ids){
        System.out.println(ids);
        try {
            shopService.patchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg(e.getMessage());
        }
    }

}
