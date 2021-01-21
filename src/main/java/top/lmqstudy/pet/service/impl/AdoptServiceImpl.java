package top.lmqstudy.pet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lmqstudy.basic.domain.Point;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.basic.util.DistanceUtil;
import top.lmqstudy.org.domain.Shop;
import top.lmqstudy.org.mapper.ShopMapper;
import top.lmqstudy.pet.domain.Adopt;
import top.lmqstudy.pet.mapper.AdoptMapper;
import top.lmqstudy.pet.service.IAdoptService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/17:07
 * @Description:
 */
@Service
public class AdoptServiceImpl extends BaseServiceImpl<Adopt> implements IAdoptService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private AdoptMapper adoptMapper;

    /**
     * @Author Mr.Li
     * @Description 发布寻主消息
     * @Date 2021/1/20 17:07
     * @Param [adopt]
     * @return void
     **/
    @Override
    public void publishAdopt(Adopt adopt) {
        //调用百度API查询用户地址
        Point point = DistanceUtil.getPoint(adopt.getAddress());
        //查询所有店铺
        List<Shop> shops = shopMapper.loadAll();
        //查询附近最近的商家店铺
        Shop nearestShop = DistanceUtil.getNearestShop(point, shops);
        //将shop信息插入到adopt中
        adopt.setShop(nearestShop);
        adopt.setShop_id(nearestShop.getId());
        //插入一条信息
        adoptMapper.save(adopt);
    }
}
