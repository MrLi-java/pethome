package top.lmqstudy.pet.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.lmqstudy.org.domain.Shop;
import top.lmqstudy.org.domain.SystemDictionaryDetail;
import top.lmqstudy.user.domain.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 宠物
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/20 16:10
 */
@Data
public class Pet {
    private Long id;
    private String name;
    private BigDecimal saleprice;
    private BigDecimal costprice;
    private String resources;
    private Integer state;
    private Long type_id;
    private PetType petType;


    private SystemDictionaryDetail coatColor;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date offsaletime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date onsaletime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime = new Date();
    private Long shop_id;
    private Shop shop;

    private Long user_id;
    private User user;

    private Long adopt_id;

    private PetDetail petDetail;
}
