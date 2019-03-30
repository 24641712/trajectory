package cn.lnu.trajectory.dao;

import cn.lnu.trajectory.model.SemStayPoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lichunting
 * @date 2018/4/18
 */
public interface SemDao {

    public int insertData( @Param("slist")List<SemStayPoint>slist);
}
