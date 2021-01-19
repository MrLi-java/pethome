package top.lmqstudy.product.service.impl;


import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.basic.util.RedisUtils;
import top.lmqstudy.product.domain.Shopcart;
import top.lmqstudy.product.mapper.ShopcartMapper;
import top.lmqstudy.product.service.IShopcartService;
import top.lmqstudy.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/18/20:42
 * @Description:
 */
@Service
public class ShopcartServiceImpl extends BaseServiceImpl<Shopcart> implements IShopcartService {
    @Autowired
    private ShopcartMapper shopcartMapper;

    @Override
    @Transactional
    public void addShopcart(Shopcart shopcart, HttpServletRequest request) {
        User user = RedisUtils.INSTANCE.getUser(request, User.class);
        Shopcart sc = shopcartMapper.loadByProductIdAndUserId(shopcart.getProduct_id(),user.getId());
        shopcart.setAmount(shopcart.getPrice().multiply(new BigDecimal(shopcart.getBuycount())));
        shopcart.setUser_id(user.getId());
        if(sc==null){
            shopcartMapper.save(shopcart);
        }else {
            sc.setBuycount(sc.getBuycount()+shopcart.getBuycount());
            sc.setAmount(sc.getAmount().add(shopcart.getAmount()));
            shopcartMapper.update(sc);
        }
    }

    @Override
    public List<Shopcart> getMyShopcarts(Long id) {
        return shopcartMapper.loadByUserId(id);
    }

    @Override
    public Integer getCounts(Long id) {
        List<Integer> counts = shopcartMapper.getBuycountByUserId(id);
        Integer sum = 0;
        for (Integer count : counts) {
            sum += count;
        }
        return sum;
    }
}
