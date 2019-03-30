package cn.lnu.trajectory.util;

import cn.lnu.trajectory.model.DataPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author modestlee
 * @date 2018/4/15
 */
public class CalculateUtil {
    private static double EARTH_RADIUS = 6378.137;
    private static List<List<DataPoint>> cluster= new ArrayList<List<DataPoint>>();
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
    class ComparatorDp implements Comparator<DataPoint> {
        public int compare(DataPoint arg0, DataPoint arg1) {
            double temp = arg0.getReachableDistance() - arg1.getReachableDistance();
            int a = 0;
            if (temp < 0) {
                a = -1;
            } else {
                a = 1;
            }
            return a;
        }
    }

    /**
     *
     * @param dataPoints 所有数据点的集合
     * @param radius 半径
     * @param ObjectNum 点的数量
     * @return
     */
    public List<DataPoint> startAnalysis(List<DataPoint> dataPoints, double radius, int ObjectNum) {
        List<DataPoint> dpList = new ArrayList<DataPoint>();
        List<DataPoint> dpQue = new ArrayList<DataPoint>();
        int count = 0;
        int total = 0;
        while (total < dataPoints.size()) {
            if (isContainedInList(dataPoints.get(total), dpList) == -1) {
                List<DataPoint> tmpDpList = isKeyAndReturnObjects(dataPoints.get(total),
                        dataPoints, radius, ObjectNum);
                if (tmpDpList != null && tmpDpList.size() > 0) {
                    DataPoint newDataPoint = new DataPoint(dataPoints.get(total));
                    dpQue.add(newDataPoint);
                }
            }
            while (!dpQue.isEmpty()) {
                DataPoint tempDpfromQ = dpQue.remove(0);
                DataPoint newDataPoint = new DataPoint(tempDpfromQ);
                dpList.add(newDataPoint);
                List<DataPoint> tempDpList = isKeyAndReturnObjects(tempDpfromQ,
                        dataPoints, radius, ObjectNum);
                System.out.println(newDataPoint.getName() + ":" + newDataPoint.getReachableDistance());
                if (tempDpList != null && tempDpList.size() > 0) {
                    for (int i = 0; i < tempDpList.size(); i++) {
                        DataPoint tempDpfromList = tempDpList.get(i);
                        int indexInList = isContainedInList(tempDpfromList,
                                dpList);
                        int indexInQ = isContainedInList(tempDpfromList, dpQue);
                        if (indexInList == -1) {
                            if (indexInQ > -1) {
                                int index = -1;
                                for (DataPoint dataPoint : dpQue) {
                                    index++;
                                    if (index == indexInQ) {
                                        if (dataPoint.getReachableDistance() > tempDpfromList
                                                .getReachableDistance()) {
                                            dataPoint
                                                    .setReachableDistance(tempDpfromList
                                                            .getReachableDistance());
                                        }
                                    }
                                }
                            } else {
                                dpQue.add(new DataPoint(tempDpfromList));
                            }
                        }
                    }

                    // TODO：对Q进行重新排序
                    Collections.sort(dpQue, new ComparatorDp());
                    List<DataPoint> temp = new ArrayList<DataPoint>();
                    for(DataPoint dp:dpQue){
                        temp.add(dp);
                    }
                    cluster.add(temp);
                    count++;
                }
            }
            System.out.println("------");
            total++;

        }

        return dpList;
    }


    public void displayDataPoints(List<DataPoint> dps) {
        for (DataPoint dp : dps) {
            System.out.println(dp.getName() + ":" + dp.getReachableDistance());
        }
    }

    public  List<List<DataPoint>> getCluster() {
        return cluster;
    }

    public static void setCluster(List<List<DataPoint>> cluster) {
        CalculateUtil.cluster = cluster;
    }

    /**
     * 判断某个点是否在队列当中
     * @param dp
     * @param dpList
     * @return
     */
    private int isContainedInList(DataPoint dp, List<DataPoint> dpList) {
        int index = -1;
        for (DataPoint dataPoint : dpList) {
            index++;
            if (dataPoint.getName().equals(dp.getName())) {
                return index;
            }
        }
        return -1;
    }


    private List<DataPoint> isKeyAndReturnObjects(DataPoint dataPoint, List<DataPoint> dataPoints, double radius, int ObjectNum) {
        List<DataPoint> arrivableObjects = new ArrayList<DataPoint>(); //用来存储所有直接密度可达对象
        List<Double> distances = new ArrayList<Double>(); //欧几里得距离
        double coreDistance; //核心距离

        for (int i = 0; i < dataPoints.size(); i++) {
            DataPoint dp = dataPoints.get(i);
            double distance = getDistance(dataPoint, dp);
            if (distance>=0 &&distance <= radius) {
                distances.add(distance);
                arrivableObjects.add(dp);
            }
        }

        if (arrivableObjects.size() >= ObjectNum) {
            List<Double> newDistances = new ArrayList<Double>(distances);
            Collections.sort(distances);
            coreDistance = distances.get(ObjectNum - 1);
            for (int j = 0; j < arrivableObjects.size(); j++) {
                if (coreDistance > newDistances.get(j)) {
                    if (newDistances.get(j) == 0) {
                        dataPoint.setReachableDistance(coreDistance);
                    }
                    arrivableObjects.get(j).setReachableDistance(coreDistance);
                } else {
                    arrivableObjects.get(j).setReachableDistance(newDistances.get(j));
                }
            }
            return arrivableObjects;
        }

        return null;
    }

    private double getDistance(DataPoint dp1, DataPoint dp2) {
        Double latitude1 = dp1.getDimensioin()[0];
        Double longitude1 = dp1.getDimensioin()[1];
        Double latitude2 = dp2.getDimensioin()[0];
        Double longitude2 = dp2.getDimensioin()[1];
        // 维度
        double lat1 = (Math.PI / 180) * latitude1;
        double lat2 = (Math.PI / 180) * latitude2;

        // 经度
        double lon1 = (Math.PI / 180) * longitude1;
        double lon2 = (Math.PI / 180) * longitude2;

        // 地球半径
        double R = 6371;

        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;

        return d * 1000;
    }
}
