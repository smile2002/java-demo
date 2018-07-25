package cn.smile.crypto;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.Security;

public class SM3 {
    public static void main(String[] args) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] message = "123".getBytes();
            MessageDigest digest = MessageDigest.getInstance("SM3", "BC");
            byte[] result = digest.digest(message);
            System.out.println(Hex.encodeHexString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
