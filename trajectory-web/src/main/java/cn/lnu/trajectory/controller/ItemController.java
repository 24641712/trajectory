package cn.lnu.trajectory.controller;

import cn.lnu.trajectory.model.Item;
import cn.lnu.trajectory.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lichunting
 * @date 2018/4/18
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemService itemService;
    @RequestMapping("/all")
    public String  getAllItem(HttpServletResponse response, HttpServletRequest request){
        List<Item> lists = itemService.getAllItem();
        request.setAttribute("actitem",lists);
        return "item";
    }

    @RequestMapping("/tfidf")
    public String tfidf(HttpServletResponse response, HttpServletRequest request){
        List<String> lists = itemService.tfidf();
        request.setAttribute("tfidf",lists);
        return "tfidf";
    }
}
