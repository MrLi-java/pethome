package top.lmqstudy.product.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.product.domain.Shopcart;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/18/20:40
 * @Description:
 */
public interface IShopcartService extends IBaseService<Shopcart> {

    void addShopcart(Shopcart shopcart, HttpServletRequest request);

    List<Shopcart> getMyShopcarts(Long id);

    Integer getCounts(Long id);
}
