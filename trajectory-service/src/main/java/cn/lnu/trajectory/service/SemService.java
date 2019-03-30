package cn.lnu.trajectory.service;

import cn.lnu.trajectory.dao.SemDao;
import cn.lnu.trajectory.dao.UserDao;
import cn.lnu.trajectory.model.SemStayPoint;
import cn.lnu.trajectory.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lichunting
 * @date 2017/12/17
 */
@Resource
@Service("semService")
public class SemService {

    @Resource
    private SemDao semDao;

   public int insertData(List<SemStayPoint> list){
       return semDao.insertData(list);
   }
}
