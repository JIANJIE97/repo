package fun.jianjie.miniorder.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeStampUtil {


    /**
     * 获取当前时间的时间戳
     * @return
     */
    public static Long dateToTimeStamp(){
        long time = new Date().getTime();
        return time;
    }

    /**
     * 把时间戳转换为字符串格式的日期
     * @param time
     * @return
     */
    public static String timeStampToString(Long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }


    public static void main(String[] args) {
        System.out.println(dateToTimeStamp());
        System.out.println(timeStampToString(new Date().getTime()));
    }
}
