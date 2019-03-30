
import cn.lnu.trajectory.dao.ItemDao;
import cn.lnu.trajectory.dao.UserDao;
import cn.lnu.trajectory.model.PointVo;
import cn.lnu.trajectory.model.User;
import cn.lnu.trajectory.service.UploadFileService;
import cn.lnu.trajectory.util.TFIDF;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lichunting
 * @date 2017/12/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/applicationContext.xml")
public class UserTest {
    @Resource
    private ItemDao itemDao;
    @Resource
    private UserDao userDao;
    @Test
    public void test()  {
        List<User> lists = userDao.getAllUser();
        List<String> keys = new ArrayList<String>();
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        for(User user:lists){
            List<String> items = itemDao.getAllItemByUid(user.getUid());
            keys.add(user.getUid());
            map.put(user.getUid(),items);
        }
        System.out.println("-----------------------------------------");
        Map<String, HashMap<String, Integer>> normal = TFIDF.NormalTFOfAll(map);
        for (String filename : normal.keySet()) {
            System.out.println("fileName " + filename);
            System.out.println("TF " + normal.get(filename).toString());
        }
        System.out.println("-----------------------------------------");
        Map<String, HashMap<String, Float>> notNarmal =  TFIDF.tfOfAll(map);
        for (String filename : notNarmal.keySet()) {
            System.out.println("fileName " + filename);
            System.out.println("TF " + notNarmal.get(filename).toString());
        }
        System.out.println("-----------------------------------------");
        Map<String, Float> idf = TFIDF.idf(keys);
        for (String word : idf.keySet()) {
            System.out.println("keyword :" + word + " idf: " + idf.get(word));
        }
        System.out.println("-----------------------------------------");
        Map<String, HashMap<String, Float>> tfidf = TFIDF.tfidf(keys,map);
        for (String filename : tfidf.keySet()) {
            System.out.println("fileName " + filename);
            System.out.println(tfidf.get(filename));
        }

    }

}
