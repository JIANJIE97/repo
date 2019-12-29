package fun.jianjie.miniorder.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderIDUtil {


    private static String arr[] = {"A","B","C","D","E","F","G","H","I","J"};


    public static String doInvoke(){


        SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");
        SimpleDateFormat MM = new SimpleDateFormat("MM");
        SimpleDateFormat DD = new SimpleDateFormat("dd");


        //1、年
        String yyyy = arr[Integer.parseInt(YYYY.format(new Date())) - 2017];


        //2、月
        String mm = intToHex(Integer.parseInt(MM.format(new Date())));
        //System.out.println(mm);


        //3、日
        String dd = DD.format(new Date());
        //System.out.println(dd);

        //4、unix时间戳后5位
        String time = Long.toString(new Date().getTime());
        String t = time.substring(time.length()-5,time.length());
        //System.out.println(time);
        //System.out.println(t);


        //5、时间纳秒值倒数第10到倒数第5
        String millis = Long.toString(System.nanoTime());
        String m = millis.substring(millis.length()-10,millis.length()-5);
        //System.out.println(millis);
        //System.out.println(m);

        //6、随机0-99的数
        Random random1 = new Random(100);
        int random= (int) (Math.random() * 100);
        String r = null;
        if(random<10){
            r = "0" + Integer.toString(random);
        }else{
            r = Integer.toString(random);
        }
        //System.out.println(random);
        //System.out.println(r);

        String result =yyyy+mm+dd+t+m+r;
        return result;
    }

    /**
     * 把十进制转换为16进制
     * @param n
     * @return
     */
    private static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            s = s.append(b[n%16]);
            n = n/16;
        }
        a = s.reverse().toString();
        return a;
    }



    public static void main(String[] args) {
        System.out.println(doInvoke());
    }
}
