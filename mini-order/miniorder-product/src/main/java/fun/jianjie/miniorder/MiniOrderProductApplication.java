package fun.jianjie.miniorder;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MiniOrderProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniOrderProductApplication.class,args);
    }
}
