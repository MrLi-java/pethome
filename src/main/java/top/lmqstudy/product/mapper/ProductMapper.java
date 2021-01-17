package top.lmqstudy.product.mapper;


import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.product.domain.Product;
import top.lmqstudy.product.domain.ProductDetail;

import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/17/16:54
 * @Description:
 */
public interface ProductMapper extends BaseMapper<Product> {


    void saveProductDetail(ProductDetail productDetail);

    void updateProductDetail(ProductDetail productDetail);

    void removeProductDetail(Long id);


    void onsale(Map<String, Object> map);

    void offsale(Map<String, Object> map);
}
