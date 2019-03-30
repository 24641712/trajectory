package cn.lnu.trajectory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lichunting
 * @date 2017/12/17
 */
@Controller
@RequestMapping("/index/")
public class IndexController {
    @RequestMapping("plt")
    public String pltIndex(){
        return "uploadfile";
    }
}
