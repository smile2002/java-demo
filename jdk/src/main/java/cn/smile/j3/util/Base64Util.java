package cn.smile.j3.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Smile on 2018/7/25.
 */
public class Base64Util {
    public static void main(String[] args) {

        /** JDK 自带 **/
        String str2 = java.util.Base64.getEncoder().encodeToString("Hello Base64!".getBytes());
        System.out.println("encoded-2: " + str2);

    }
}
