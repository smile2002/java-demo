package cn.smile.commons.codec;

/**
 * Created by Smile on 2018/12/27.
 */
public class Scale62 {
    private static String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static int scale = 62;


    public static String encode(long num, int length) {
        StringBuilder sb = new StringBuilder();
        int remainder = 0;

        while (num > scale - 1) {
            remainder = Long.valueOf(num % scale).intValue();
            sb.append(chars.charAt(remainder));

            num = num / scale;
        }

        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        String value = sb.reverse().toString();
        return value;
    }

    public static long decode(String str) {
        str = str.replace("^0*", "");
        long num = 0;
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            index = chars.indexOf(str.charAt(i));
            num += (long) (index * (Math.pow(scale, str.length() - i - 1)));
        }
        return num;
    }

    public static void main(String[] args) {
        //System.out.println("62进制：" + encode(2576460752303423487L, 11));

        System.out.println("62进制：" + encode(                  0L ,11));
        System.out.println("62进制：" + encode(                 99L ,11));

        System.out.println("10进制：" + decode("34KDUNMZwuV"));
    }
}
