package top.lmqstudy.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.product.domain.Product;
import top.lmqstudy.product.query.ProductQuery;
import top.lmqstudy.product.service.IProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/17/15:13
 * @Description:
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * @Author Mr.Li
     * @Description 根据商家id进行分页查询和高级查询
     * @Date 2021/1/17 16:59
     * @Param [query, request]
     * @return top.lmqstudy.basic.util.PageList<top.lmqstudy.product.domain.Product>
     **/
    @PostMapping
    public PageList<Product> QueryData(@RequestBody ProductQuery query, HttpServletRequest request){
        return productService.queryDataByShopId(query,request);
    }


    /**
     * @Author Mr.Li
     * @Description 商品添加或者修改
     * @Date 2021/1/17 19:16
     * @Param [product]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PutMapping
    public AjaxResult saveAndUpdate(@RequestBody Product product,HttpServletRequest request){
        try {
            productService.saveAndUpdate(product,request);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("操作失败！！");
        }
    }

    /**
     * @Author Mr.Li
     * @Description 删除数据
     * @Date 2021/1/17 19:54
     * @Param [id]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @DeleteMapping("/{id}")
    public AjaxResult del(@PathVariable("id") Long id){
        try {
            productService.del(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("操作失败！！！");
        }
    }

    /**
     * @Author Mr.Li
     * @Description 商品批量上架
     * @Date 2021/1/17 20:11
     * @Param [ids]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/

    @PatchMapping("/onsale")
    public AjaxResult onsale(@RequestBody List<Long> ids){
        try {
            productService.onsale(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上架失败");
        }
    }


    /**
     * @Author Mr.Li
     * @Description 商品批量下架
     * @Date 2021/1/17 20:37
     * @Param [ids]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PatchMapping("/offsale")
    public AjaxResult offsale(@RequestBody List<Long> ids){
        try {
            productService.offsale(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上架失败");
        }
    }
}
