package cn.smile.j3.util;
import org.apache.commons.codec.binary.Hex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Smile on 2018/12/28.
 */
public class UUIDDemo {


    /** 方式一：移位 **/
    public static long byte2Long(byte[] buf) {
        int len = buf.length>8?8:buf.length;
        long num = 0;
        for (int i=0; i<len; i++) {
            num <<= 8;
            num |= (buf[i] & 0xff);
        }
        return num;
    }

    /** 方式二：java.nio.ByteBuffer **/
    public static long byte2Long2(byte[] buf) {
        ByteBuffer buffer = ByteBuffer.wrap(buf, 0, 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        return buffer.getLong();
    }

    public static void main(String[] args) throws Exception{
        int count = 0;
        long lastNum = 0;
                while(true) {
            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            String sect1 = uuidStr.substring(0, 8);
            String sect3 = uuidStr.substring(24);

            byte[] rslt1 = Hex.decodeHex(sect1);
            byte[] rslt3 = Hex.decodeHex(sect3);

            byte[] tmp3 = new byte[8];
            System.arraycopy(rslt3,0,tmp3,2,6);

            long num3 = byte2Long(rslt3) % 1000000;

            System.currentTimeMillis();

            if (lastNum == num3) {
                System.out.println("出现连续相同1次： last = " + lastNum + " curr = " + num3);
            }
            lastNum = num3;
            if (++count % 1000000 == 0) {
                System.out.println(count);
            }
            if (true) {
                System.out.println(uuidStr + "    " + sect1 + " " + byte2Long(rslt1) + "    " + sect3 + " " + byte2Long2(tmp3));
                break;
            }
        }

    }
}
