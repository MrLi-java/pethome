package top.lmqstudy.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.pet.domain.PetType;
import top.lmqstudy.pet.service.IPetTypeService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/18:15
 * @Description:
 */
@RestController
@RequestMapping("/pettype")
public class PetTypeController {
    @Autowired
    private IPetTypeService petTypeService;

    /**
     * @Author Mr.Li
     * @Description 得到所有的宠物品种
     * @Date 2021/1/20 18:18
     * @Param []
     * @return java.util.List<top.lmqstudy.pet.domain.PetType>
     **/
    @GetMapping
    public List<PetType> getPetTypes(){
        return petTypeService.getPetTypes();
    }
}
