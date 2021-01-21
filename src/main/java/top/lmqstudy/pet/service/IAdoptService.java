package top.lmqstudy.pet.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.pet.domain.Adopt;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/17:06
 * @Description:
 */
public interface IAdoptService extends IBaseService<Adopt> {
    /**
     * @Author Mr.Li
     * @Description 发布寻主消息
     * @Date 2021/1/20 17:06
     * @Param [adopt]
     * @return void
     **/
    void publishAdopt(Adopt adopt);
}
