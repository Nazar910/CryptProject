package nazar.pyvovar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by pyvov on 19.02.2017.
 */
@SpringBootApplication
@ComponentScan("nazar.pyvovar")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
