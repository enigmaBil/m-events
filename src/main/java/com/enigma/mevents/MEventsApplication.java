package com.enigma.mevents;

import com.enigma.mevents.controllers.auth.AuthenticateService;
import com.enigma.mevents.controllers.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.enigma.mevents.doa.entities.Role.ADMIN;

@SpringBootApplication
public class MEventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MEventsApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(
//            AuthenticateService service
//    ){
//        return args -> {
//            var admin = RegisterRequest.builder()
//                    .name("Admin")
//                    .email("admin@mail.com")
//                    .password("password")
//                    .role(ADMIN)
//                    .build();
//            System.out.println("Admin access Token: "+ service.register(admin).getAccessToken());
//        };
//    }

}
