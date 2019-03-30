package cn.lnu.trajectory.dao;

import cn.lnu.trajectory.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lichunting
 * @date 2018/4/17
 */
public interface UserDao {

    public List<User> getAllUser();

    public List<String> getAllDate(String uid);

    public User getUserByUid(@Param("uid")String uid);

    public Integer insertUser(User user);
}
