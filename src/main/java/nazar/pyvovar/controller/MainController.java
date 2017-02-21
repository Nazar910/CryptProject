package nazar.pyvovar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by pyvov on 21.02.2017.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}
