package nazar.pyvovar.config;

import nazar.pyvovar.crypt.algorithm.caesar.CaesarImpl;
import nazar.pyvovar.crypt.algorithm.caesar.CryptAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pyvov on 19.02.2017.
 */
@Configuration
public class AppConfig {

    @Bean
    public CryptAlgorithm setupCaesarImpl() {
        String englishAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int key = 1;
        return new CaesarImpl(englishAlphabet, key);
    }

}
