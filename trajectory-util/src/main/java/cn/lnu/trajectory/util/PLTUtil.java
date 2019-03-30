package cn.lnu.trajectory.util;

import cn.lnu.trajectory.model.Point;


import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lichunting
 * @date 2017/11/15
 */
public class PLTUtil {

    public static List<Point> getPoints(InputStream ins,String uid) throws UnsupportedEncodingException {
        List<Point> points = new ArrayList<Point>();
        InputStreamReader insReader = new InputStreamReader(ins, "UTF-8");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(insReader);
            String tempstring = null;
            int line = 1;
            while ((tempstring = reader.readLine()) != null) {
                if (line > 0) {
                    String[] temps = tempstring.split(",");
                    Point point = new Point();
                    point.setUid(uid);
                    point.setLatitude(Double.parseDouble(temps[0]));
                    point.setLongitude(Double.parseDouble(temps[1]));
                    point.setAltitude(Double.parseDouble(temps[3]));
                    point.setDate(temps[5]);
                    point.setTime(temps[6]);
                    points.add(point);
                }
                line++;
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
        }

        return points;

    }

    public static void traverseFolder1(String path) throws FileNotFoundException, UnsupportedEncodingException {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        List<Point> points = new ArrayList<Point>();
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {
                    System.out.println("文件:" + file2.getAbsolutePath());
                    fileNum++;
                }
                FileInputStream fileInputStream = new FileInputStream(file2);
                points.addAll( getPoints(fileInputStream,"000"));


            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);

    }
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        traverseFolder1("C:\\Users\\lichunting\\Desktop\\final\\000\\Trajectory");
    }
}
