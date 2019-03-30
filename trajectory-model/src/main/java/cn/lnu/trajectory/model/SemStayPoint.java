package cn.lnu.trajectory.model;

/**
 * @author lichunting
 * @date 2018/4/18
 */
public class SemStayPoint {

    private Integer id;

    private String mapsem;

    private String duration;

    private String datetype;

    public SemStayPoint() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMapsem() {
        return mapsem;
    }

    public void setMapsem(String mapsem) {
        this.mapsem = mapsem;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDatetype() {
        return datetype;
    }

    public void setDatetype(String datetype) {
        this.datetype = datetype;
    }
}
