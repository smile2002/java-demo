package cn.smile.commons.codec;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Smile on 2018/7/25.
 */
public class Base64Util {
    public static void main(String[] args) {

        try {
            String str = "Hello Base64!";
            System.out.println("input  : " + str);

            String encoded = Base64.encodeBase64String(str.getBytes());
            System.out.println("encoded: " + encoded);
            byte[] decoded = Base64.decodeBase64(encoded);
            System.out.println("decoded: " + new String(decoded));
        }catch(Exception e) {
            e.printStackTrace();
        }


        /** JDK 自带 **/
        String str2 = java.util.Base64.getEncoder().encodeToString("Hello Base64!".getBytes());
        System.out.println("encoded-2: " + str2);

    }
}
