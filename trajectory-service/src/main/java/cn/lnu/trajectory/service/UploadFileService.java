package cn.lnu.trajectory.service;

import cn.lnu.trajectory.dao.PointDao;
import cn.lnu.trajectory.dao.UserDao;
import cn.lnu.trajectory.model.Point;
import cn.lnu.trajectory.model.PointVo;
import cn.lnu.trajectory.model.User;
import cn.lnu.trajectory.util.PLTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lichunting
 * @date 2017/12/17
 */
@Resource
@Service("uploadFileService")
public class UploadFileService {

    @Resource
    private PointDao pointDao;
    @Resource
    private UserDao userDao;

    public Boolean batchInserPoints(InputStream ins,String uid) throws UnsupportedEncodingException {
        List<Point> points = PLTUtil.getPoints(ins,uid);
        User user = userDao.getUserByUid(uid);
        if(user==null){
            User user1 = new User();
            user1.setUid(uid);
            userDao.insertUser(user1);
        }
        Integer result = pointDao.batchInsertPoints(points);
        if(result>0)
            return true;
        else
            return false;
    }

    public List<PointVo> getPointsById(String uid){
        List<PointVo> pointVos = pointDao.getDatasById(uid);
        System.out.println(pointVos);
        return pointVos;
    }

    public List<PointVo> getPointsOneDay(String uid,String sdate){
        List<PointVo> pointVos = pointDao.getPointOndDay(uid,sdate);
        return pointVos;
    }

}
