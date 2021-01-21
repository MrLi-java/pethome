package top.lmqstudy.pet.domain;

import lombok.Data;

/**
 * 宠物详情
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/21 11:35
 */
@Data
public class PetDetail {
    private Long id;
    private Long pet_id;
    private String adoptNotice;
    private String intro;

}
