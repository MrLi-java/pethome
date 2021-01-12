package top.lmqstudy.org.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.lmqstudy.basic.domain.BaseDomain;
import java.util.Date;

@Data
public class Shop  extends BaseDomain {


  private String name;
  private String tel;
  private Date registerTime = new Date();
  private Integer state = 1;
  private String address;
  private String logo;
  private Long admin_id;
  private Employee admin;

  @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
  public Date getRegisterTime() {
    return registerTime;
  }
}
