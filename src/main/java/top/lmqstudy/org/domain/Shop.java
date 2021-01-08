package top.lmqstudy.org.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.lmqstudy.basic.domain.BaseDomain;
import java.util.Date;

@Data
public class Shop  extends BaseDomain {


  private String name;
  private String tel;
  private Date registerTime;
  private Integer state;
  private String address;
  private String logo;
  private Long admin_id;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  public Date getRegisterTime() {
    return registerTime;
  }
}
