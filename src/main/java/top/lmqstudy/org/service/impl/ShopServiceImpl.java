package top.lmqstudy.org.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.MD5Utils;
import top.lmqstudy.basic.util.StrUtils;
import top.lmqstudy.org.domain.Employee;
import top.lmqstudy.org.domain.Shop;
import top.lmqstudy.org.mapper.EmployeeMapper;
import top.lmqstudy.org.mapper.ShopMapper;
import top.lmqstudy.org.service.IShopService;



@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * @Author Mr.Li
     * @Description 商家入驻
     * @Date 2021/1/12 20:30
     * @Param [shop]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @Override
    @Transactional
    public AjaxResult settledIn(Shop shop) {
        //先保存员工信息
        Employee admin = shop.getAdmin();
        admin.setSalt(StrUtils.getComplexRandomString(32));
        admin.setPassword(MD5Utils.encrypByMd5(admin.getPassword()+admin.getSalt()));
        employeeMapper.save(admin);

        //保存店铺信息
        shopMapper.save(shop);
        admin.setShop_id(shop.getId());
        employeeMapper.update(admin);

        return AjaxResult.me();
    }
}
