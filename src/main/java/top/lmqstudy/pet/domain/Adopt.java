package top.lmqstudy.pet.domain;


import lombok.Data;
import top.lmqstudy.org.domain.Shop;
import top.lmqstudy.org.domain.SystemDictionaryDetail;
import top.lmqstudy.user.domain.User;

import java.math.BigDecimal;

/**
 * 宠物寻主消息
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/20 11:43
 */
@Data
public class Adopt {
    private Long id;
    private String title;
    private String name;
    private Double age;
    private Integer gender;
    private Long coat_color;
    private SystemDictionaryDetail coatColor;
    private String resources;
    private Long pet_type;
    private PetType petType;
    private BigDecimal price;
    private String address;
    private Integer state = 1;
    private Long user_id;
    private User user;
    private Long shop_id;
    private Shop shop;
}
