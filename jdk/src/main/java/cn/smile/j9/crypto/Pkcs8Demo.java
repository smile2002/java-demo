package cn.smile.j9.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Smile on 2019/1/7.
 */
public class Pkcs8Demo {
    public static void main(String[] args) {

        try {
            System.out.println("Hello World!");
            //FileInputStream fis = new FileInputStream("D:\\IDEAProjects\\java-demo\\jdk\\src\\main\\resources\\pkcs8_pri.key");
            FileInputStream fis = new FileInputStream("pkcs8_pri.key");

            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            System.out.println(new String(buffer));


            Security.addProvider(new BouncyCastleProvider());
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            PEMReader reader = new PEMReader(new InputStreamReader(bais));

            KeyPair keyPair = (KeyPair) reader.readObject();

            if (keyPair == null) {
                System.out.println("keyPair extract failed!");
                System.exit(1);
            }

            reader.close();
            PublicKey pubk = keyPair.getPublic();

            if (pubk == null) {
                System.out.println("pub key extract failed!");
                System.exit(1);
            }
            System.out.println(pubk);
            PrivateKey prik = keyPair.getPrivate();

            if (prik == null) {
                System.out.println("pri key extract failed!");
                System.exit(1);
            }
            System.out.println(prik);

            KeySpec keySpec = new X509EncodedKeySpec(pubk.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            System.out.println(keyFactory.generatePublic(keySpec));

            KeySpec keySpec2 = new PKCS8EncodedKeySpec(prik.getEncoded());
            System.out.println(keyFactory.generatePrivate(keySpec2));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
