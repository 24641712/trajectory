package cn.lnu.trajectory.service;

import cn.lnu.trajectory.dao.PointDao;
import cn.lnu.trajectory.dao.UserDao;
import cn.lnu.trajectory.model.Point;
import cn.lnu.trajectory.model.PointVo;
import cn.lnu.trajectory.model.User;
import cn.lnu.trajectory.util.PLTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author lichunting
 * @date 2017/12/17
 */
@Resource
@Service("userService")
public class UserService {

    @Resource
    private UserDao userDao;

   public List<User> getAllUser(){
       return userDao.getAllUser();
   }

   public List<String> getAllDate(String uid){
       return userDao.getAllDate(uid);
    }
}
