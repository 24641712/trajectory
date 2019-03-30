package cn.lnu.trajectory.model;

import java.io.Serializable;

/**
 * @author lichunting
 * @date 2017/12/16
 */
public class PointVo implements Serializable {

    private double latitude;  //纬度

    private double longtitude;  //经度
    private String stime;  //时间

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longtitude;
    }

    public void setLongitude(double longitude) {
        this.longtitude = longitude;
    }

    public String getTime() {
        return stime;
    }

    public void setTime(String time) {
        this.stime = time;
    }

    @Override
    public String toString() {
        return "PointVo{" +
                "latitude=" + latitude +
                ", longtitude=" + longtitude +
                ", time='" + stime + '\'' +
                '}';
    }
}
