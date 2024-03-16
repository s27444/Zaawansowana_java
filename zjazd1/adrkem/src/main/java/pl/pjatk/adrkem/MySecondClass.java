package pl.pjatk.adrkem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class MySecondClass {
    @Bean
    public MyFirstClass myFirstClass(List<String> defaultData){
        System.out.println(defaultData);
        return new MyFirstClass();
    }
    @Bean
    public List<String> defaultData(@Value("${my.custom.property:Hello from application properties}") String appName){
        System.out.println(appName);
        return List.of("1", "2", "3", "4", "5");
    }

}
