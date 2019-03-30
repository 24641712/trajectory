package cn.lnu.trajectory.controller;

import cn.lnu.trajectory.model.PointVo;
import cn.lnu.trajectory.service.UploadFileService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author lichunting
 * @date 2017/12/17
 */
@Controller
@RequestMapping("/upload")
public class UploadFileController {

    @Resource
    private UploadFileService uploadFileService;
    @RequestMapping("/plt")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream in = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        String uid = request.getParameter("uid");
        if (file.isEmpty()) {
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        Boolean result = uploadFileService.batchInserPoints(in,uid);

        JSONObject jsonObject = new JSONObject();
        if(result){
         jsonObject.put("status","success");
        }else{
            jsonObject.put("status","error");
        }

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("status","error");
        }
        out.print(jsonObject);

    }

    @RequestMapping("/getDataById")
    public void getDataById(String uid){
        List<PointVo> pointVoList = uploadFileService.getPointsById(uid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("datas","success");


    }
}
