package kr.co.hhjpetclinicstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HhjPetClinicStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhjPetClinicStudyApplication.class, args);
    }

}
