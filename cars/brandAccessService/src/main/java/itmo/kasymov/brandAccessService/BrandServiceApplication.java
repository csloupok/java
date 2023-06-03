package itmo.kasymov.brandAccessService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "itmo.kasymov")
@EntityScan("itmo.kasymov.*")
public class BrandServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BrandServiceApplication.class, args);
    }
}