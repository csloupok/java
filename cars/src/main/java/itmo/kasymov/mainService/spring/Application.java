package itmo.kasymov.mainService.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "itmo.kasymov")
@EntityScan("itmo.kasymov.mainService.entity")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
