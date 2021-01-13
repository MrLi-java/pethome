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


    /**
     * @Author Mr.Li
     * @Description 系统平台员工审核
     * @Date 2021/1/13 9:19
     * @Param [id, type] type :  type :  1表示不通过，2表示驳回，3表示通过
     * @return void
     **/
    void shopAudit(Long id, Integer type,String auditMsg);

    /**
     * @Author Mr.Li
     * @Description 商家激活
     * @Date 2021/1/13 10:34
     * @Param [id]
     * @return void
     **/
    void shopRegActive(Long id);
}

