package com.proxy;

import com.proxy.service.AddressService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    //@Bean
    //public AddressService getAddressService(){
       // return new AddressService();
    //}

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setAdditionalProfiles(args);
        application.run();
    }

}
