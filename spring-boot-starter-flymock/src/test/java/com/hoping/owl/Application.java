package com.hoping.owl;

import com.hoping.owl.starter.model.EnableFlyMock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by houping wang on 2019/4/23
 *
 * @author houping wang
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.hoping"})
@EnableFlyMock
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
