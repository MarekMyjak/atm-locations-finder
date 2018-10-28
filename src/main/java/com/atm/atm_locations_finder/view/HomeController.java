package com.atm.atm_locations_finder.view;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @ApiOperation(value = "Return simple HTML page with shows the use of the application.")
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
