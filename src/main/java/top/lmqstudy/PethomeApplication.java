package top.lmqstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.lmqstudy.*.mapper")
public class PethomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PethomeApplication.class,args);
    }
}
