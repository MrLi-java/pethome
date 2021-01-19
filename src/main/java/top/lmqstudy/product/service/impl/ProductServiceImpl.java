package top.lmqstudy.product.service.impl;

import io.swagger.models.auth.In;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.basic.util.JsonUtils;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.basic.util.RedisUtils;
import top.lmqstudy.org.domain.Employee;
import top.lmqstudy.product.domain.Product;
import top.lmqstudy.product.domain.ProductDetail;
import top.lmqstudy.product.mapper.ProductMapper;
import top.lmqstudy.product.query.ProductQuery;
import top.lmqstudy.product.service.IProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/17/16:52
 * @Description:
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * @Author Mr.Li
     * @Description 根据商家id进行分页查询和高级查询
     * @Date 2021/1/17 17:47
     * @Param [query, request]
     * @return top.lmqstudy.basic.util.PageList<top.lmqstudy.product.domain.Product>
     **/
    @Override
    public PageList<Product> queryDataByShopId(ProductQuery query, HttpServletRequest request) {
        //根据userToken获取redis存储的登录用户信息
        String userToken = request.getHeader("userToken");
        String jsonStr = RedisUtils.INSTANCE.get(userToken);
        Employee employee = JsonUtils.toObject(jsonStr, Employee.class);
        //将shopid存入分页条件
        query.setShopId(employee.getShop_id());
        //查询总数
        Long count = productMapper.queryCount(query);
        //查询分页数据
        List<Product> list = productMapper.queryData(query);
        return new PageList<>(count,list);
    }

    /**
     * @Author Mr.Li
     * @Description 商品添加或者修改
     * @Date 2021/1/17 19:17
     * @Param [product]
     * @return void
     **/
    @Override
    @Transactional
    public void saveAndUpdate(Product product,HttpServletRequest request) {
        Employee employee = RedisUtils.INSTANCE.getUser(request, Employee.class);
        if(product.getId() == null){
            product.setShop_id(employee.getShop_id());
            productMapper.save(product);
            ProductDetail productDetail = product.getProductDetail();
            productDetail.setProduct_id(product.getId());
            productMapper.saveProductDetail(productDetail);
        }else {
            productMapper.update(product);
            productMapper.updateProductDetail(product.getProductDetail());
        }
    }



    /**
     * @Author Mr.Li
     * @Description filePath
     * @Date 2021/1/17 19:55
     * @Param [id]
     * @return void
     **/
    @Override
    @Transactional
    public void del(Long id) {
        productMapper.remove(id);
        productMapper.removeProductDetail(id);
    }

    /**
     * @Author Mr.Li
     * @Description 商品批量上架
     * @Date 2021/1/17 20:16
     * @Param [ids]
     * @return void
     **/
    @Override
    @Transactional
    public void onsale(List<Long> ids) {
        Map<String,Object> map = new HashMap<>();
        map.put("onsaletime",new Date());
        map.put("offsaletime",null);
        map.put("ids",ids);
        productMapper.onsale(map);
    }

    /**
     * @Author Mr.Li
     * @Description 商品批量下架
     * @Date 2021/1/17 20:38
     * @Param [ids]
     * @return void
     **/
    @Override
    @Transactional
    public void offsale(List<Long> ids) {
        Map<String,Object> map = new HashMap<>();
        map.put("offsaletime",new Date());
        map.put("ids",ids);
        productMapper.offsale(map);
    }

}
