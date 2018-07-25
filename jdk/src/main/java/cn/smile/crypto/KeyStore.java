package cn.smile.crypto;

import java.io.FileInputStream;
import java.net.URL;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.util.Enumeration;


public class KeyStore {
    public static void printPrivateKeyInfo(PrivateKey privateKey)
    {
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        System.out.println("----------RSAPrivateKey ----------");
        System.out.println("Modulus.length=" + rsaPrivateKey.getModulus().bitLength());
        System.out.println("Modulus=" + rsaPrivateKey.getModulus().toString());
        System.out.println("PrivateExponent.length=" + rsaPrivateKey.getPrivateExponent().bitLength());
        System.out.println("PrivatecExponent=" + rsaPrivateKey.getPrivateExponent().toString());

    }

    public static void main( String[] args )
    {
        /**
         * Java中不同类型的密钥库包含：
         *    PrivateKey
         *    SecretKey
         *    JKS
         *    PKCS12
         *    JCEKS
         *    等
         */
        try {
            java.security.KeyStore ks = java.security.KeyStore.getInstance("PKCS12");

            URL url = KeyStore.class.getClassLoader().getResource("test1.p12");
            /** url.getFile() 是代表绝对路径的字符串 **/
            FileInputStream fis = new FileInputStream(url.getFile());
            ks.load(fis, "000000".toCharArray());
            fis.close();

            System.out.println("keystore type = " + ks.getType());

            Enumeration enum1 = ks.aliases();
            String keyAlias = null;
            // we are readin just one certificate.
            while (enum1.hasMoreElements()) {
                keyAlias = (String)enum1.nextElement();
                System.out.println("Alias = [" + keyAlias + "]");
            }

            System.out.println("is key entry = " + ks.isKeyEntry(keyAlias));

            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, "000000".toCharArray());
            Certificate cert = ks.getCertificate(keyAlias);
            Certificate[] chain = ks.getCertificateChain(keyAlias);
            System.out.println("certification chain length = " + chain.length);
            for(int i = 0; i < chain.length; i++) {
                Certificate c = chain[i];
                System.out.printf("cert[%d] : %s\n", i, c.getType());
            }

            PublicKey pubkey = cert.getPublicKey();
            System.out.println("public key = " + pubkey);
            KeyStore.printPrivateKeyInfo(prikey);


            System.out.println("\n\n===================================================================");
            System.out.println("========                    CERT CHAIN                     ========");
            System.out.println("===================================================================\n");
            System.out.println("cert class = " + cert.getClass().getName());
            System.out.println("cert type  = " + cert.getType());
            System.out.println("cert = " + cert);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
