package io.iseongbeen.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller  // RestController 아님!
class HomeController {

    @GetMapping("/")
    fun index(): String {
        return "forward:/index.html"
    }
}