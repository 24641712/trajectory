package cn.lnu.trajectory.util;

import cn.lnu.trajectory.model.Item;

import java.util.List;

/**
 * @author lichunting
 * @date 2018/4/19
 */
public class SimilityUtil {

    public static Double getSimility(List<Item> item1,List<Item> item2){
        int length=0;
        if(item1.size()>item2.size()){
            length = item2.size();
        }else{
            length = item1.size();
        }
        double result = 0.0,temp=0.0,temp2=0.0,temp3=0.0;
      for (int i = 0;i<length;i++){
            temp += item1.get(i).getWeight()*item2.get(i).getWeight();
      }
        for (int i = 0;i<length;i++){
            temp2 += item1.get(i).getWeight()*item1.get(i).getWeight();
            temp3 += item2.get(i).getWeight()*item2.get(i).getWeight();
        }

        result=Math.cos(temp/(Math.sqrt(temp2+temp3)));
        return result;
    }
}
