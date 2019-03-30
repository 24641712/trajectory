import java.util.ArrayList;
import java.util.List;

/**
 * @author modestlee
 * @date 2018/4/16
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        List<Integer> list2= new ArrayList<Integer>();
        list.add(2);
        list.add(0);

        list2.addAll(list);
       for(Integer i:list2){
           System.out.println(i);
       }

    }
}
