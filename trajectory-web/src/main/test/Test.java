import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lichunting
 * @date 2018/4/17
 */
public class Test {
    public static void main(String[] args) {
        String strDate ="02:02:02";
        SimpleDateFormat sDateFormat=new SimpleDateFormat("HH:mm:ss"); //加上时间
        //必须捕获异常
        try {
            Date date=sDateFormat.parse(strDate);

            System.out.println(date.getTime());
        } catch(ParseException px) {
            px.printStackTrace();
        }
    }
}
