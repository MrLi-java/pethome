package top.lmqstudy.pet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.pet.domain.PetType;
import top.lmqstudy.pet.mapper.PetTypeMapper;
import top.lmqstudy.pet.service.IPetTypeService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/18:19
 * @Description:
 */
@Service
public class PetTypeServiceImpl extends BaseServiceImpl<PetType> implements IPetTypeService {
    @Autowired
    private PetTypeMapper petTypeMapper;

    /**
     * @Author Mr.Li
     * @Description 得到所有的宠物品种
     * @Date 2021/1/20 18:19
     * @Param []
     * @return java.util.List<top.lmqstudy.pet.domain.PetType>
     **/
    @Override
    public List<PetType> getPetTypes() {
        return petTypeMapper.getPetTypes();
    }
}
