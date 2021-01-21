package top.lmqstudy.org.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.org.controller.SystemDictionaryDetailController;
import top.lmqstudy.org.domain.SystemDictionaryDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/17:55
 * @Description:
 */
public interface ISystemDictionaryDetailService extends IBaseService<SystemDictionaryDetail> {
    /**
     * @Author Mr.Li
     * @Description 得到宠物毛色信息
     * @Date 2021/1/20 17:56
     * @Param [sn]
     * @return java.util.List<top.lmqstudy.org.controller.SystemDictionaryDetailController>
     **/
    List<SystemDictionaryDetailController> getCoatColor(String sn);
}
