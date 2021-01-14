package top.lmqstudy.org.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.basic.contant.Contant;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.MD5Utils;
import top.lmqstudy.basic.util.MailUtils;
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

    @Autowired
    private MailUtils mailUtils;

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

    /**
     * @Author Mr.Li
     * @Description 平台员工审核
     * @Date 2021/1/13 9:20
     * @Param [id, type]   type :  1表示不通过，2表示驳回，3表示通过
     * @return void
     **/
    @Override
    @Transactional
    public void shopAudit(Long id, Integer type,String auditMsg) {
        Shop shop = shopMapper.loadById(id);
        switch (type){
            case 1:
                Employee admin1 = employeeMapper.loadById(shop.getAdmin_id());
                //发送邮件
                mailUtils.send(admin1.getEmail(),"审核未通过",auditMsg);
                System.out.println(shop.getName() + "的审核操作完成！");
                //将该用户的数据从数据库中删除
                employeeMapper.remove(shop.getAdmin_id());
                //将该店铺数据从数据库中删除
                shopMapper.remove(id);
                break;
            case 2:
                //店铺状态设为禁用状态
                shop.setState(Contant.STATE_DISABLED);
                shopMapper.update(shop);
                //工状态设为禁用状态
                Employee admin2 = employeeMapper.loadById(shop.getAdmin_id());
                admin2.setState(Contant.STATE_DISABLED);
                employeeMapper.update(admin2);
                //发送邮件
                String content = "<h2 style='color:red;'>抱歉，您审核被驳回！原因为：</h2><br/><h2 style='color:red;'>"+auditMsg+"</h2><br/>"
                        + "<br/>点击以下链接即可再次提交！<br/><br/>" +
                        "<a href='http://172.16.80.252:8081/#/settledIn?id="+id+"'>点击修改信息</a>";
                mailUtils.send(admin2.getEmail(),"审核驳回",content);
                System.out.println(shop.getName() + "的审核操作完成！");
                break;
            case 3:
                //店铺状态设为待激活状态
                shop.setState(Contant.STATE_ACTIVE);
                shopMapper.update(shop);

                //员工状态设为待激活状态
                //System.out.println(shop.getAdmin());
                Employee admin3 = employeeMapper.loadById(shop.getAdmin_id());
                admin3.setState(Contant.STATE_ACTIVE);
                employeeMapper.update(admin3);

                //发送激活邮件
                String content2 = "<h2 style='color:red;'>恭喜你成功入驻宠物之家平台！</h2><br/><br/>"
                        + "点击以下链接即可激活！<br/><br/>" +
                        "<a href='http://172.16.80.252:8080/lr/shopRegActive/"+id+"'>点击激活</a>";
                mailUtils.send(admin3.getEmail(),"入驻激活",content2);
                System.out.println(shop.getName() + "的审核操作完成！");
                break;
        }
    }

    /**
     * @Author Mr.Li
     * @Description 商家激活
     * @Date 2021/1/13 10:35
     * @Param [id]
     * @return void
     **/
    @Override
    @Transactional
    public void shopRegActive(Long id) {
        //店铺状态设为正常
        Shop shop = shopMapper.loadById(id);
        shop.setState(Contant.STATE_NORMAL);
        shopMapper.update(shop);

        //员工状态设为正常
        Employee admin = employeeMapper.loadById(shop.getAdmin_id());
        admin.setState(Contant.STATE_NORMAL);
        employeeMapper.update(admin);
    }
}
