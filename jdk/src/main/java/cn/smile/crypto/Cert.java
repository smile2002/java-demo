package cn.smile.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.security.cert.X509Certificate;
import java.io.FileInputStream;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;


public class Cert {

    public static void printPublicKeyInfo(PublicKey publicKey)
    {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        System.out.println("----------RSAPublicKey----------");
        System.out.println("Modulus.length=" + rsaPublicKey.getModulus().bitLength());
        System.out.println("Modulus=" + rsaPublicKey.getModulus().toString());
        System.out.println("PublicExponent.length=" + rsaPublicKey.getPublicExponent().bitLength());
        System.out.println("PublicExponent=" + rsaPublicKey.getPublicExponent().toString());
    }

    public static void main( String[] args )
    {
        String x509Cert = "MIIDzjCCAragAwIBAgIKGNDz/H99Hd/CxjANBgkqhkiG9w0BAQUFADBZMQswCQYD" +
                "VQQGEwJDTjEwMC4GA1UEChMnQ2hpbmEgRmluYW5jaWFsIENlcnRpZmljYXRpb24g" +
                "QXV0aG9yaXR5MRgwFgYDVQQDEw9DRkNBIFRFU1QgQ1MgQ0EwHhcNMTIwODMwMDMx" +
                "NDMzWhcNMzEwNTExMDMxNDMzWjBYMQswCQYDVQQGEwJDTjEwMC4GA1UEChMnQ2hp" +
                "bmEgRmluYW5jaWFsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MRcwFQYDVQQDEw5D" +
                "RkNBIFRFU1QgT0NBMTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALiL" +
                "J/BrdvHSbXNfLIMTwUg9tDtVjMRGXOl6aZnu9IpxjI5SMUJ4hVwgJnmbTokxs6GF" +
                "IXKsCLSm5H1jHLI22ysc/ltByEybLWj5jjJuC9+Uknbl3/Ls1RBG6MogUCqZckuo" +
                "hKrf5DmlV3C/jVLxGn3pUeanvmqVUi4TKpXxgm5QqKSPF8VtQY4qCpNcQwwZqbMr" +
                "D+IfJtfpGAeVrP+Kg6i1t65seeEnVSaLhqpRUDU0PTblOuUv3OhiKJWA3cYWxUrg" +
                "7U7SIHNJLSEUWmjy4mKty+g7Cnjzt29F9qXFb6oB2mR8yt4GHCilw1Rc5RBXY63H" +
                "eTuOwdtGE3M2p7Q++OECAwEAAaOBmDCBlTAfBgNVHSMEGDAWgBR03sWNCn0QGqpp" +
                "g1tNIc6Gm8xxODAMBgNVHRMEBTADAQH/MDgGA1UdHwQxMC8wLaAroCmGJ2h0dHA6" +
                "Ly8yMTAuNzQuNDIuMy90ZXN0cmNhL1JTQS9jcmwxLmNybDALBgNVHQ8EBAMCAQYw" +
                "HQYDVR0OBBYEFM9wnWHrnXwuuPfLAkD3CZ3+M3SAMA0GCSqGSIb3DQEBBQUAA4IB" +
                "AQC0JOazrbkk0XMxMMeBCc3lgBId1RjQLgWUZ7zaUISpPstGIrE5A9aB6Ppq0Sxl" +
                "pt2gkFhPEKUqgOFN1CzCDEbP3n4H0chqK1DOMrgTCD8ID5UW+ECTYNe35rZ+1JiF" +
                "lOPEhFL3pv6XSkiKTfDnjum8+wFwUBGlfoWK1Hcx0P2Hk1jcZZKwGTx1IAkesF83" +
                "pufhxHE2Ur7W4d4tfp+eC7XXcA91pdd+VUrAfkj9eKHcDEYZz66HvHzmt6rtJVBa" +
                "pwrtCi9pW3rcm8c/1jSnEETZIaokai0fD7260h/LkD/GrNCibSWxFj1CqyP9Y5Yv" +
                "cj6aA5LnUcJYeNkrQ3V4XvVc";

        try {
            /**
             * 方式一：使用javax.security.cert.X509Certificate进行解析
             * 两种初始化方法： A) InputStream  B) byte[]
             */
            X509Certificate pubCert = X509Certificate.getInstance(Base64.decodeBase64(x509Cert));
            PublicKey publicKey = pubCert.getPublicKey();
            Cert.printPublicKeyInfo(publicKey);


            /**
             * 方式二：使用java.security.cert.X509Certificate进行解析
             * （只能用InputStream）
             */
            URL url = Cert.class.getClassLoader().getResource("pubCert.crt");
            System.out.println("公钥所在路径:"+ url.getFile());
            FileInputStream is = new FileInputStream(url.getFile());
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            java.security.cert.X509Certificate cert = (java.security.cert.X509Certificate)cf.generateCertificate(is);
            publicKey = cert.getPublicKey();
            Cert.printPublicKeyInfo(publicKey);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
