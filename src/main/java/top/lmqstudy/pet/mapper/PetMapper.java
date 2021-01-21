package top.lmqstudy.pet.mapper;

import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.pet.domain.Pet;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/20:57
 * @Description:
 */
public interface PetMapper extends BaseMapper<Pet> {

    /**
     * @Author Mr.Li
     * @Description 批量上架
     * @Date 2021/1/20 22:49
     * @Param [map]
     * @return void
     **/
    void onsale(Map<String, Object> map);


    /**
     * @Author Mr.Li
     * @Description 批量下架
     * @Date 2021/1/20 22:49
     * @Param [map]
     * @return void
     **/
    void offsale(Map<String, Object> map);
}
