package top.lmqstudy.org.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lmqstudy.basic.domain.BaseDomain;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseDomain {

  private String username;
  private String email;
  private String phone;
  private String salt;
  private String password;
  private Integer age;
  private Integer state;
  private Department department;
  private Long logininfo_id;
  private Long shop_id;




}
