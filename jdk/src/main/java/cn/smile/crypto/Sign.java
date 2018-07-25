package cn.smile.crypto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class Sign {
    public static void main(String[] args) {
        String priKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDAp9Qy+eW7E4oh" +
                "tgydlE0cCMFtU4ueS+zZpbAYnegmMQJYWRMhlbNuZHFsn5NEPspq9JOrlT+ZNaAB" +
                "O0QCSRi3pY90+hVJxJ/LqPZ3n0qD1xPVZHbC9b428VCnBO0iRakfN648zqbfS6oS" +
                "XurJUw1vXd40vqFH8yzRQnUMEaQrTkTeYsk7WAVsomSZVIrEWpYraFtaBLovbv4H" +
                "HZDouErMmPXoUY7dEVcsdng7NKoLYLKEX8p8++xavOKmmDDUZLQm//orZ9dKNyFU" +
                "oALQn9iNF1m1cu7+T1M9pCLM8JgUJmJ3iYL8LZC7nN+OCpDI5VfQxpdsb09XImy0" +
                "IHJQtnALAgMBAAECggEBAINFF7qQOe+JddJYiCoK9auHb7y9c2Gt3DnEwsNTPO5g" +
                "8HcgYn+80CEFAaAA70hN/IB6G/CK5mXPezG7nzBrfBMOFTygdkboWfVcMGv7trs0" +
                "nogFR0K6TRyEuu7C1Y1Hp9/kga1fJab6mGyiDhqVo47hh9mqlpw+3qfLP5O0pYb3" +
                "QesNQAwG2FRoz7rpact78fQLSPg4+BQ3fY3PH3PCD4NO83qohqWb8YYDjdRlFhbw" +
                "3HzxIvK5NnL5DUkRvnCd0IUe6FYWiiyP9dpqpxoKJ1gTPAKnhhvyIKDL4GBHyRtU" +
                "K6HmewQt8wRTukZF20o/O+6lnFMNxARxjCsqbeJN1UECgYEA+qaQR2g4+mOHOCnp" +
                "LJWfDZn2XiMaiID1e7II79nB9aB9ANv+lpJAlw3LJlhGp1WtvyGsm8oL1/NnSd1+" +
                "yS8g6meCQ6lyhMlb7/Q2h/I4B1NR89VzfBF7H2ZcOfvRLAv8s6WxPFcM5S4BJjop" +
                "uoESx/ydGkC/wAE9szRkfRrH42sCgYEAxMRoZEUzHhKoAABm8Z7OKx3+9x+SMAAx" +
                "agpjpiw+Hibw9k5Np19XYq4zsBa59w97OCV4OIUniFCcu/HBodTy+LBamjXbBLyd" +
                "VUjzSer1gJMjE+s/k+0ngkRCYcpXAWIZGRbGnomDQbTFUt7NK4/LmeP8wXlAJzhf" +
                "L6Azm5FZbeECgYEA0XygnW24IV1JyGEO5xpuAropNOh15pyRjz5vRbdj6lyl3TAB" +
                "CetWXGlE3tlsw7JSMqp8TwCBAY4HR679pjvjcMXuE4rtkZmRY21iniCNP2Id3oMJ" +
                "sCoUE1xDvJqAaiXioGrdRKckue7HOyHlemEiR08UJrrzCBCBduNVEVJuud0CgYAy" +
                "QDqsyONqHbeb8H5Z3IWoi0uPDxgFfDcibq7ORGREzQsizBKW2Ql3ISgAI32ws9pz" +
                "F1ahkJfs/zFX6kDaNIksq9UnpvNdPVIs8ql3SjMSeAEwWBbvj1RUt1wAn20i5Eum" +
                "laN534KO6DywkkfxhRKpJYlqW63flkzw28fyReeB4QKBgQD5JON/dyCqoQ2fMxrv" +
                "SRoDxkdqm5W2srlDoA1GnqZ2Vf1sZwgsBJjR1ER98dJW8IxA0bawxev5ABiaCB9Z" +
                "3Jm47l7EYTOZf99WikMFzx0FhY3BCjx2BeidZO+3TtDfK6tA/3U4otP87p7sDgEj" +
                "07pBIb/qyPgO2CxJUm8C5olBig==";

        try {
            String signData = "Sign Text Data";
            byte[] priKeyBytes = Base64.decodeBase64(priKey);
            PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(priKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(priKeySpec);

            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateKey);
            signature.update(signData.getBytes());
            byte[] signed = signature.sign();
            System.out.println("sign = " + Hex.encodeHexString(signed));



            String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwKfUMvnluxOKIbYMnZRN" +
                    "HAjBbVOLnkvs2aWwGJ3oJjECWFkTIZWzbmRxbJ+TRD7KavSTq5U/mTWgATtEAkkY" +
                    "t6WPdPoVScSfy6j2d59Kg9cT1WR2wvW+NvFQpwTtIkWpHzeuPM6m30uqEl7qyVMN" +
                    "b13eNL6hR/Ms0UJ1DBGkK05E3mLJO1gFbKJkmVSKxFqWK2hbWgS6L27+Bx2Q6LhK" +
                    "zJj16FGO3RFXLHZ4OzSqC2CyhF/KfPvsWrzippgw1GS0Jv/6K2fXSjchVKAC0J/Y" +
                    "jRdZtXLu/k9TPaQizPCYFCZid4mC/C2Qu5zfjgqQyOVX0MaXbG9PVyJstCByULZw" +
                    "CwIDAQAB";
            byte[] pubKeyBytes = Base64.decodeBase64(pubKey);
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);

            Signature signVerify = Signature.getInstance("SHA1WithRSA");
            signVerify.initVerify(publicKey);
            signVerify.update(signData.getBytes());
            boolean verified = signVerify.verify(signed);
            System.out.println("verify result : " + verified);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
