package top.lmqstudy.org.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.org.domain.Shop;


public interface IShopService extends IBaseService<Shop> {

    /**
     * @Author Mr.Li
     * @Description 商家入驻
     * @Date 2021/1/12 20:30
     * @Param [shop]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    AjaxResult settledIn(Shop shop);
}

