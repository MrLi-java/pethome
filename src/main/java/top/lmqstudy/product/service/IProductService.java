package top.lmqstudy.product.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.product.domain.Product;
import top.lmqstudy.product.query.ProductQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/17/16:52
 * @Description:
 */
public interface IProductService extends IBaseService<Product> {
    PageList<Product> queryDataByShopId(ProductQuery query, HttpServletRequest request);

    /**
     * @Author Mr.Li
     * @Description 商品添加或者修改
     * @Date 2021/1/17 19:16
     * @Param [product]
     * @return void
     **/
    void saveAndUpdate(Product product,HttpServletRequest request);

    /**
     * @Author Mr.Li
     * @Description 商品批量上架
     * @Date 2021/1/17 20:13
     * @Param [ids]
     * @return void
     **/
    void onsale(List<Long> ids);

    /**
     * @Author Mr.Li
     * @Description 商品批量下架
     * @Date 2021/1/17 20:38
     * @Param [ids]
     * @return void
     **/
    void offsale(List<Long> ids);
}
