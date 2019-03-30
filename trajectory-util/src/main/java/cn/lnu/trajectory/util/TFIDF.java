package cn.lnu.trajectory.util;


import java.io.IOException;

import java.util.*;

/**
 * @author lichunting
 * @date 2018/4/18
 */
public class TFIDF {
    private static HashMap<String, HashMap<String, Float>> allTheTf = new HashMap<String, HashMap<String, Float>>();
    private static HashMap<String, HashMap<String, Integer>> allTheNormalTF = new HashMap<String, HashMap<String, Integer>>();


    public static HashMap<String, Integer> normalTF(String[] cutWordResult) {
        HashMap<String, Integer> tfNormal = new HashMap<String, Integer>();//没有正规化
        int wordNum = cutWordResult.length;
        int wordtf = 0;
        for (int i = 0; i < wordNum; i++) {
            wordtf = 0;
            if (cutWordResult[i] != " ") {
                for (int j = 0; j < wordNum; j++) {
                    if (i != j) {
                        if (cutWordResult[i].equals(cutWordResult[j])) {
                            cutWordResult[j] = " ";
                            wordtf++;

                        }
                    }
                }
                tfNormal.put(cutWordResult[i], ++wordtf);
                cutWordResult[i] = " ";
            }
        }
        return tfNormal;
    }

    public static HashMap<String, Float> tf(String[] cutWordResult) {
        HashMap<String, Float> tf = new HashMap<String, Float>();//正规化
        int wordNum = cutWordResult.length;
        int wordtf = 0;
        for (int i = 0; i < wordNum; i++) {
            wordtf = 0;
            for (int j = 0; j < wordNum; j++) {
                if (cutWordResult[i] != " " && i != j) {
                    if (cutWordResult[i].equals(cutWordResult[j])) {
                        cutWordResult[j] = " ";
                        wordtf++;
                    }
                }
            }
            if (cutWordResult[i] != " ") {
                tf.put(cutWordResult[i], (new Float(++wordtf)) / wordNum);
                cutWordResult[i] = " ";
            }
        }
        return tf;
    }


    public static Map<String, Float> idf(List<String> fileList)  {
        //公式IDF＝log((1+|D|)/|Dt|)，其中|D|表示文档总数，|Dt|表示包含关键词t的文档数量。
        Map<String, Float> idf = new HashMap<String, Float>();
        List<String> located = new ArrayList<String>();

        float Dt = 1;
        float D = allTheNormalTF.size();//文档总数
        List<String> key = fileList;//存储各个文档名的List
        Map<String, HashMap<String, Integer>> tfInIdf = allTheNormalTF;//存储各个文档tf的Map

        for (int i = 0; i < D; i++) {
            HashMap<String, Integer> temp = tfInIdf.get(key.get(i));
            for (String word : temp.keySet()) {
                Dt = 1;
                if (!(located.contains(word))) {
                    for (int k = 0; k < D; k++) {
                        if (k != i) {
                            HashMap<String, Integer> temp2 = tfInIdf.get(key.get(k));
                            if (temp2.keySet().contains(word)) {
                                located.add(word);
                                Dt = Dt + 1;
                                continue;
                            }
                        }
                    }
                    idf.put(word, Log.log((1 + D) / Dt, 10));
                }
            }
        }
        return idf;
    }

    public static Map<String, HashMap<String, Float>> tfidf(List<String> keys,Map<String,List<String>> maps)  {

        Map<String, Float> idf = idf(keys);
        Map<String, HashMap<String, Float>> tf = tfOfAll(maps);

        for (String file : tf.keySet()) {
            Map<String, Float> singelFile = tf.get(file);
            for (String word : singelFile.keySet()) {
                singelFile.put(word, (idf.get(word)) * singelFile.get(word));
            }
        }
        return tf;
    }


    public static Map<String, HashMap<String, Integer>> NormalTFOfAll(Map<String,List<String>> maps)  {

       for(String key:maps.keySet()){
           List<String> lists = maps.get(key);
        if(!lists.isEmpty()){
            String[] arrs = new String[lists.size()];
            lists.toArray(arrs);
            HashMap<String,Integer> dict = normalTF(arrs);
            allTheNormalTF.put(key,dict);
        }

       }
        return allTheNormalTF;
    }


    public static Map<String, HashMap<String, Float>> tfOfAll(Map<String,List<String>> maps){
        for(String key:maps.keySet()){
            List<String> lists = maps.get(key);
            if(!lists.isEmpty()){
                String[] arrs = new String[lists.size()];
                lists.toArray(arrs);
                HashMap<String,Float> dict = tf(arrs);
                allTheTf.put(key,dict);
            }

        }
        return allTheTf;
    }


}
