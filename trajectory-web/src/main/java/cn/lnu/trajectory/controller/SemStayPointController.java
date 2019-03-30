package cn.lnu.trajectory.controller;

import cn.lnu.trajectory.model.DataPoint;
import cn.lnu.trajectory.model.PointVo;
import cn.lnu.trajectory.model.SemStayPoint;
import cn.lnu.trajectory.service.SemService;
import cn.lnu.trajectory.service.UploadFileService;
import cn.lnu.trajectory.util.CalculateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author modestlee
 * @date 2018/4/15
 */
@Controller
@RequestMapping("/staypoint")
public class SemStayPointController {

    @Resource
    private UploadFileService uploadFileService;
    @Resource
    private SemService semService;

    @RequestMapping("/sem")
    public String getSem(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
      HttpSession session = request.getSession();
      List<DataPoint> list = (List<DataPoint>) session.getAttribute("points");
      List<DataPoint> points1=list.subList(20,30);
        List<DataPoint> points2=list.subList(60,90);
        List<DataPoint> points3=list.subList(100,103);
        List<DataPoint> points4=list.subList(103,113);
        request.setAttribute("points1",points1);
        request.setAttribute("points2",points2);
        request.setAttribute("points3",points3);
        request.setAttribute("points4",points4);


      return "sem";
    }

    @RequestMapping("/getpoints")
    public String getPoints(){
        return "optics";
    }

    @RequestMapping("/getpointsp")
    public String getPointsp(){
        return "opticsp";
    }
    @RequestMapping("/showReach")
    public void showReach(HttpServletRequest request, HttpServletResponse response,String uid,String sdate) throws ParseException {
        List<PointVo> list = uploadFileService.getPointsOneDay(uid,sdate);
        ArrayList<DataPoint> dpoints = new ArrayList<DataPoint>();
        int i = 0;
        for(PointVo pv:list){
            double[] temp = new double[2];
            temp[0] = pv.getLatitude();
            temp[1] = pv.getLongitude();
            DataPoint dp = new DataPoint(temp,i+"");
            dpoints.add(dp);
            i=i+1;
        }
        CalculateUtil clu = new CalculateUtil();
        List<DataPoint> listp = clu.startAnalysis(dpoints,10,5);
        List<List<DataPoint>> cluster = clu.getCluster();

        List<DataPoint> newList=new ArrayList<DataPoint>();
        for(List<DataPoint> listpoint:cluster){
            if(!listpoint.isEmpty()) {
                int max = Integer.parseInt(listpoint.get(0).getName());
                int min = Integer.parseInt(listpoint.get(0).getName());
                for (DataPoint dataPoint : listpoint) {
                    int temp = Integer.parseInt(dataPoint.getName());
                    if (temp > max) {
                        max = temp;
                    } else if (temp < min) {
                        min = temp;
                    }
                }
                SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm:ss"); //加上时间
                long times = sDateFormat.parse(list.get(max).getTime()).getTime() - sDateFormat.parse(list.get(min).getTime()).getTime();
                System.out.println(times);
                if(times>240000){
                    for(DataPoint point:listpoint){
                        if(!newList.contains(point)){
                            newList.add(point);
                        }
                    }
                }

            }


        }
        System.out.println(newList.size()+":"+listp.size());

       // clu.displayDataPoints(listp);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        jsonObject.put("datas",listp);
        jsonObject.put("datas2",newList);
        HttpSession session = request.getSession();
        session.setAttribute("points",newList);

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("status","error");
        }
        out.print(jsonObject);
    }

    @RequestMapping("/showReachp")
    public void showReachp(HttpServletRequest request, HttpServletResponse response,String uid,String sdate) throws ParseException {
       showReach(request,response,uid,sdate);
    }

}
