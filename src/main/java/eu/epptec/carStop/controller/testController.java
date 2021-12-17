package eu.epptec.carStop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
@RequestMapping("/test")
public class testController {
    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public testController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @GetMapping
    public String test(){
        return "Hello";
    }

    @GetMapping("/endpointdoc")
    public void show(Model model) {
        model.addAttribute("handlerMethods", this.handlerMapping.getHandlerMethods());
    }
}
