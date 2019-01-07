package cn.smile.j9.crypto;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.Security;

public class SM3 {
    public static void main(String[] args) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            byte[] message = "阿布塞麦提·阿卜杜2605247733981747".getBytes("utf-8");
            //byte[] message = "1628158557804998667293".getBytes();

            MessageDigest digest = MessageDigest.getInstance("SM3", "BC");
            byte[] result = digest.digest(message);
            System.out.println(Hex.encodeHexString(result).toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
