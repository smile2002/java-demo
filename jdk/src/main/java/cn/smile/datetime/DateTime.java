package cn.smile.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTime {
    public static void main(String[] args) {

        /** Date **/
        Date date1 = new Date();

        DateFormat df1 = DateFormat.getDateTimeInstance();
        System.out.println("getDateTimeInstance : " + df1.format(date1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("format(Date)        : " + sdf.format(date1));

        /** long **/
        long current = System.currentTimeMillis();
        System.out.println("format(long)        : " + sdf.format(current));

        /** Date -> long **/
        current = date1.getTime();
        System.out.println("Date.getTime()      : " + sdf.format(current));

        /** long -> Date **/
        Date date2 = new Date(current);
        System.out.println("new Date(long)      : " + sdf.format(date2));


        /** 解析日期时间字符串 **/
        String time = "1983-06-01 12:34:56";
        try {
            Date oldDate = sdf.parse(time);
            long oldTime = oldDate.getTime();
            System.out.println("Parsed Date         : " + sdf.format(oldTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
