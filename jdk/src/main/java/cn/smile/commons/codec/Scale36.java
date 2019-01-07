package cn.smile.commons.codec;

/**
 * Created by Smile on 2018/12/27.
 */
public class Scale36 {
    private static String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static int scale = 36;


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

    public static void main(String[] args) throws Exception{
        Long curMs1 = System.currentTimeMillis();
        Thread.sleep(100);
        Long curMs2 = System.currentTimeMillis();
        System.out.println(curMs1);
        System.out.println(curMs2);

        System.out.println("62进制：" + encode(9999999999999999L, 11));
        System.out.println("10进制：" + decode("zzzz"));
    }
}

