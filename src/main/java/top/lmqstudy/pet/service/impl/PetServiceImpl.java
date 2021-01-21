package top.lmqstudy.pet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.pet.domain.Adopt;
import top.lmqstudy.pet.domain.Pet;
import top.lmqstudy.pet.domain.PetDetail;
import top.lmqstudy.pet.mapper.AdoptMapper;
import top.lmqstudy.pet.mapper.PetMapper;
import top.lmqstudy.pet.service.IPetService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/20:49
 * @Description:
 */
@Service
public class PetServiceImpl extends BaseServiceImpl<Pet> implements IPetService {
    @Autowired
    private AdoptMapper adoptMapper;

    @Autowired
    private PetMapper petMapper;
    /**
     * @Author Mr.Li
     * @Description 店铺处理结果
     * @Date 2021/1/20 20:49
     * @Param [id, handleResult]
     * @return void
     **/
    @Override
    @Transactional
    public void handleAdopt(Long id, Integer handleResult) {
        if(handleResult == 1){
            Adopt adopt = adoptMapper.loadById(id);
            adopt.setState(0);
            adopt.setUser_id(null);
            adoptMapper.update(adopt);

            //3.创建一个宠物对象，保存到t_pet表中，状态默认为下架状态(-1) 不设置user_id,该宠物已经变成商家店铺的宠物
            Pet pet = new Pet();
            pet.setName(adopt.getName());
            pet.setCostprice(adopt.getPrice());
            pet.setResources(adopt.getResources());
            pet.setState(-1);       //下架状态
            pet.setType_id(adopt.getPetType().getId());
            pet.setShop_id(adopt.getShop_id());
            pet.setAdopt_id(id);
            petMapper.save(pet);

            PetDetail petDetail = new PetDetail();
            petDetail.setPet_id(pet.getId());
            petDetail.setIntro(adopt.getTitle());
            petDetail.setAdoptNotice("年龄："+adopt.getAge()+",性别："+adopt.getGender());
            petMapper.savePetDetail(petDetail);
        }else {

        }

    }

    /**
     * @Author Mr.Li
     * @Description 宠物添加和修改
     * @Date 2021/1/20 22:17
     * @Param [pet]
     * @return void
     **/
    @Override
    public void saveAndUpdate(Pet pet) {
        if(pet.getId() == null){
            petMapper.save(pet);
        }else {
            petMapper.update(pet);
        }
    }

    /**
     * @Author Mr.Li
     * @Description 宠物批量上架
     * @Date 2021/1/20 22:48
     * @Param [ids]
     * @return void
     **/

    @Override
    public void onsale(List<Long> ids) {
        Map<String,Object> map = new HashMap<>();
        map.put("onsaletime",new Date());
        map.put("offsaletime",null);
        map.put("ids",ids);
        petMapper.onsale(map);
    }

    /**
     * @Author Mr.Li
     * @Description 宠物批量下架
     * @Date 2021/1/20 22:48
     * @Param [ids]
     * @return void
     **/
    @Override
    public void offsale(List<Long> ids) {
        Map<String,Object> map = new HashMap<>();
        map.put("offsaletime",new Date());
        map.put("ids",ids);
        petMapper.offsale(map);
    }
}
