package cn.lnu.trajectory.controller;

import cn.lnu.trajectory.model.User;
import cn.lnu.trajectory.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author lichunting
 * @date 2018/4/17
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/getalluser")
    public void getAllUser(HttpServletRequest request, HttpServletResponse response){
        List<User> users= userService.getAllUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        jsonObject.put("datas",users);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("status","error");
        }
        out.print(jsonObject);
    }

    @RequestMapping("/getalldate")
    public void getAllDate(HttpServletRequest request, HttpServletResponse response,String uid){
        List<String> dates= userService.getAllDate(uid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        jsonObject.put("datas",dates);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("status","error");
        }
        out.print(jsonObject);
    }
}
