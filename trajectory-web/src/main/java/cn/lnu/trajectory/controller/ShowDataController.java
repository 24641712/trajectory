package cn.lnu.trajectory.controller;

import cn.lnu.trajectory.model.PointVo;
import cn.lnu.trajectory.service.UploadFileService;
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
 * @date 2018/4/5
 */
@RequestMapping("/data")
@Controller
public class ShowDataController {

    @Resource
    private UploadFileService uploadFileService;

    @RequestMapping("/data")
    public String data(){
        return "userdata";
    }
    @RequestMapping("/oneday")
    public String oneDay(){
        return "oneday";
    }



    @RequestMapping("/showdata")
    public void showData(HttpServletRequest request, HttpServletResponse response,String usernumber){
        List<PointVo> pointVoList = uploadFileService.getPointsById(usernumber);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        jsonObject.put("datas",pointVoList);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("status","error");
        }
        out.print(jsonObject);
    }


    @RequestMapping("/onedaydata")
    public void oneDayData(HttpServletRequest request, HttpServletResponse response,String uid,String did){
        List<PointVo> pointVoList = uploadFileService.getPointsOneDay(uid,did);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        jsonObject.put("datas",pointVoList);
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
