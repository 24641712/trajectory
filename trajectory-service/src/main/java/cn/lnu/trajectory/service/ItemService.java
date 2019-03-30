package cn.lnu.trajectory.service;

import cn.lnu.trajectory.dao.ItemDao;
import cn.lnu.trajectory.dao.UserDao;
import cn.lnu.trajectory.model.Item;
import cn.lnu.trajectory.model.User;
import cn.lnu.trajectory.util.TFIDF;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lichunting
 * @date 2017/12/17
 */
@Resource
@Service("itemService")
public class ItemService {
    @Resource
    private ItemDao itemDao;
    @Resource
    private UserDao userDao;

    public List<Item>  getAllItem(){
        return itemDao.getAllItem();
    }

    public List<String> tfidf(){
        List<User> lists = userDao.getAllUser();
        List<String> keys = new ArrayList<String>();
        List<String> re=new ArrayList<String>();
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        for(User user:lists){
            List<String> items = itemDao.getAllItemByUid(user.getUid());
            keys.add(user.getUid());
            map.put(user.getUid(),items);
        }
        System.out.println("-----------------------------------------");
        Map<String, HashMap<String, Integer>> normal = TFIDF.NormalTFOfAll(map);
        String str1 = "";
        for (String filename : normal.keySet()) {
            str1+="uid " + filename+"<br>"+"TF " + normal.get(filename).toString()+"<br>";
            System.out.println("uid " + filename);
            System.out.println("TF " + normal.get(filename).toString());
        }
        System.out.println("-----------------------------------------");
        String str2 = "";
        Map<String, HashMap<String, Float>> notNarmal =  TFIDF.tfOfAll(map);
        for (String filename : notNarmal.keySet()) {
            str2+="uid " + filename+"<br>"+"TF " + notNarmal.get(filename).toString()+"<br>";
            System.out.println("uid " + filename);
            System.out.println("TF " + notNarmal.get(filename).toString());
        }
        System.out.println("-----------------------------------------");
        String str3 = "";
        Map<String, Float> idf = TFIDF.idf(keys);
        for (String word : idf.keySet()) {
            str3+="keyword :" + word + " idf: " + idf.get(word)+"<br>";
            System.out.println("keyword :" + word + " idf: " + idf.get(word));
        }
        System.out.println("-----------------------------------------");
        String str4 = "";
        Map<String, HashMap<String, Float>> tfidf = TFIDF.tfidf(keys,map);
        for (String filename : tfidf.keySet()) {
            str4+="uid " + filename+"<br>"+tfidf.get(filename)+"<br>";
            System.out.println("uid " + filename);
            System.out.println(tfidf.get(filename));
        }
        re.add(str1);
        re.add(str2);
        re.add(str3);
        re.add(str4);
        return re;
    }

}
