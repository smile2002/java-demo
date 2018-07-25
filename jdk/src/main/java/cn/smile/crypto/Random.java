package cn.smile.crypto;

import java.security.SecureRandom;

public class Random {
    public static void main(String[] args) {

        try {
            SecureRandom sr = SecureRandom.getInstanceStrong();
            System.out.println(sr.nextLong());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
