package top.lmqstudy.order.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.order.domain.AdoptOrder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/21/19:49
 * @Description:
 */
public interface IAdoptOrderService extends IBaseService<AdoptOrder> {
    /**
     * @Author Mr.Li
     * @Description 前台用户领养宠物下单
     * @Date 2021/1/21 19:49
     * @Param [adoptOrder]
     * @return void
     **/
    void orderAdopt(AdoptOrder adoptOrder);
}
