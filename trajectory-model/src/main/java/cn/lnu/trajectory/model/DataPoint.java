package cn.lnu.trajectory.model;

import java.util.Arrays;

/**
 * @author lichunting
 * @date 2018/4/6
 */
public class DataPoint {
    private String name; // 样本点名
    private double dimensioin[]; // 样本点的维度
    private double coreDistance; //核心距离，如果该点不是核心对象，则距离为-1
    private double reachableDistance; //可达距离

    public DataPoint(){
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "name='" + name + '\'' +
                ", dimensioin=" + Arrays.toString(dimensioin) +
                ", coreDistance=" + coreDistance +
                ", reachableDistance=" + reachableDistance +
                '}';
    }

    public DataPoint(DataPoint e){
        this.name=e.name;
        this.dimensioin=e.dimensioin;
        this.coreDistance=e.coreDistance;
        this.reachableDistance=e.reachableDistance;
    }

    public DataPoint(double dimensioin[], String name){
        this.name=name;
        this.dimensioin=dimensioin;
        this.coreDistance=-1;
        this.reachableDistance=-1;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double[] getDimensioin() {
        return dimensioin;
    }
    public void setDimensioin(double[] dimensioin) {
        this.dimensioin = dimensioin;
    }
    public double getCoreDistance() {
        return coreDistance;
    }
    public void setCoreDistance(double coreDistance) {
        this.coreDistance = coreDistance;
    }
    public double getReachableDistance() {
        return reachableDistance;
    }
    public void setReachableDistance(double reachableDistance) {
        this.reachableDistance = reachableDistance;
    }
}
