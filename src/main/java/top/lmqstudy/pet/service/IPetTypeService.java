package top.lmqstudy.pet.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.pet.domain.PetType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/18:17
 * @Description:
 */
public interface IPetTypeService extends IBaseService<PetType> {
    /**
     * @Author Mr.Li
     * @Description 得到所有的宠物品种
     * @Date 2021/1/20 18:19
     * @Param []
     * @return java.util.List<top.lmqstudy.pet.domain.PetType>
     **/
    List<PetType> getPetTypes();
}
