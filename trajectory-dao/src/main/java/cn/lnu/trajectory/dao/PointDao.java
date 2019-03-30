package cn.lnu.trajectory.dao;

import cn.lnu.trajectory.model.Point;
import cn.lnu.trajectory.model.PointVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lichunting
 * @date 2017/12/18
 */
public interface PointDao {

    /**
     * 增加收集的数据
     * @param points
     * @return
     */
    public Integer batchInsertPoints(@Param("points") List<Point> points);


    public List<PointVo> getDatasById(@Param("uid")String uid);

    public List<PointVo> getPointOndDay(@Param("uid")String uid,@Param("sdate")String sdate);

}
