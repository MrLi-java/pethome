package top.lmqstudy.pet.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.pet.domain.Pet;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/20:45
 * @Description:
 */
public interface IPetService extends IBaseService<Pet> {
    /**
     * @Author Mr.Li
     * @Description 店铺处理结果
     * @Date 2021/1/20 20:48
     * @Param [id, handleResult]
     * @return void
     **/
    void handleAdopt(Long id, Integer handleResult);

    /**
     * @Author Mr.Li
     * @Description 宠物添加和修改
     * @Date 2021/1/20 22:17
     * @Param [pet]
     * @return void
     **/
    void saveAndUpdate(Pet pet);

    /**
     * @Author Mr.Li
     * @Description 宠物批量上架
     * @Date 2021/1/20 22:47
     * @Param [ids]
     * @return void
     **/
    void onsale(List<Long> ids);


    /**
     * @Author Mr.Li
     * @Description 宠物批量下架
     * @Date 2021/1/20 22:47
     * @Param [ids]
     * @return void
     **/
    void offsale(List<Long> ids);
}
