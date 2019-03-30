package cn.lnu.trajectory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lichunting
 * @date 2018/4/18
 */
@Controller
@RequestMapping("/model")
public class ModelController {

    @RequestMapping("/show")
    public String showModel(){
        return "Model";
    }
    @RequestMapping("/end")
    public String end(){
        return "end";
    }
}
