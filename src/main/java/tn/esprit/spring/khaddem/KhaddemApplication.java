package tn.esprit.spring.khaddem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KhaddemApplication {

    public static void main(String[] args) {
        SpringApplication.run(KhaddemApplication.class, args);
    }

}
