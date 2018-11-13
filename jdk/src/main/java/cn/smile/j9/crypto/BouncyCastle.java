package cn.smile.j9.crypto;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;


public class BouncyCastle {
    public static void main(String[] args) {
        try {
            /** AES/ECB/PKCS7Padding 不在标准JDK Cipher 支持算法列表里 **/
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "AES"));
            byte[] result = cipher.doFinal("QWEASDZS".getBytes("UTF-8"));

            System.out.println("result = " + Hex.encodeHexString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
