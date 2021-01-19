package top.lmqstudy.product.mapper;

import org.apache.ibatis.annotations.Param;
import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.product.domain.Shopcart;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/18/20:43
 * @Description:
 */
public interface ShopcartMapper extends BaseMapper<Shopcart> {
    Shopcart loadByProductIdAndUserId(@Param("productId") Long productId, @Param("id") Long id);

    List<Shopcart> loadByUserId(Long id);

    List<Integer> getBuycountByUserId(Long id);
}
