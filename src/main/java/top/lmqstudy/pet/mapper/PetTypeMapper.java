package top.lmqstudy.pet.mapper;

import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.pet.domain.PetType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/18:20
 * @Description:
 */
public interface PetTypeMapper extends BaseMapper<PetType> {

    /**
     * @Author Mr.Li
     * @Description 得到所有的宠物品种
     * @Date 2021/1/20 18:20
     * @Param []
     * @return java.util.List<top.lmqstudy.pet.domain.PetType>
     **/
    List<PetType> getPetTypes();
}
