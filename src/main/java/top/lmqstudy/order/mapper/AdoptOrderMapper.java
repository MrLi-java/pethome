package top.lmqstudy.order.mapper;

import org.apache.ibatis.annotations.Param;
import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.order.domain.AdoptOrder;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/21/19:51
 * @Description:
 */
public interface AdoptOrderMapper extends BaseMapper<AdoptOrder> {
    void updateStateAndPaySn(@Param("id") Long id, @Param("paySn") String paySn, @Param("lastConfirmTime") Date lastConfirmTime);
}
