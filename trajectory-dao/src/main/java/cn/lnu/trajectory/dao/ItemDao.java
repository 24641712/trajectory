package cn.lnu.trajectory.dao;

import cn.lnu.trajectory.model.Item;
import cn.lnu.trajectory.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lichunting
 * @date 2018/4/17
 */
public interface ItemDao {

    public List<Item> getAllItem();

    public List<String> getAllItemByUid(@Param("uid") String uid);
}
