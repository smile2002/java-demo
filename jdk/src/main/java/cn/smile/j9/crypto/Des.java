package cn.smile.j9.crypto;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by Smile on 2018/7/25.
 */
public class Des {
    public static void main( String[] args )
    {
        /** 明文、密钥、初始向量 **/
        String plainText = "Hello Crypto!";
        String password = "12345678";
        byte[] iv = {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };

        /** 准备工作：
         I. 构造KeySpec
         II. 获取SecretKeyFactory实例
         III. 生成SecretKey（用于初始化 Cipher）**/
        try {
            DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFactory.generateSecret(desKeySpec);

            /** 加密（1)获取实例、2)init、3)doFinal）**/
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, new IvParameterSpec(iv));
            byte[] cipherText = cipher.doFinal(plainText.getBytes());

            /** 解密结果输出 **/
            System.out.println("desKeySpec.getKey()    = " + Hex.encodeHexString(desKeySpec.getKey()));
            System.out.println("secretKey.getEncoded() = " + Hex.encodeHexString(secretkey.getEncoded()));
            System.out.println("cipher text            = " + Hex.encodeHexString(cipherText));

            /** 解密（1)获取实例、2)init、3)doFinal）**/
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretkey, new IvParameterSpec(iv));
            byte[] result = cipher.doFinal(cipherText);
            System.out.println("plain text             = " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
