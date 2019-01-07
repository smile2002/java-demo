package cn.smile.commons.codec;

import org.apache.commons.codec.binary.Hex;


public class HexUtil {

    public static void main(String[] args) {
        byte[] hexArray = {0x1A, 0x08, 0x07, 0x06};

        try {
            String hexString = Hex.encodeHexString(hexArray);
            System.out.println(hexString);

            byte[] result = Hex.decodeHex(hexString);
            for (int i=0; i<result.length;i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
