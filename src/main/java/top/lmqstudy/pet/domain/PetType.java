package top.lmqstudy.pet.domain;

import lombok.Data;

/**
 * 宠物类型
 * @author Administrator
 * @version 1.0
 * @date 2021/1/20 15:06
 */
@Data
public class PetType {
    private Long id;
    private String name;
    private String description;
    private Long pid;
    private PetType parent;
}
