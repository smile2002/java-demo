package cn.smile.commons.codec;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Smile on 2018/12/28.
 */
public class Byte2Long {
    /** 方式一：移位 **/
    public static long bytes2Long(byte[] buf) {
        int len = buf.length>8?8:buf.length;
        long num = 0;
        for (int i=0; i<len; i++) {
            num <<= 8;
            num |= (buf[i] & 0xff);
        }
        return num;
    }

    /** 方式二：java.nio.ByteBuffer **/
    public static long bytes2Long2(byte[] buf) {
        ByteBuffer buffer = ByteBuffer.wrap(buf, 0, 8);
        buffer.order(ByteOrder.BIG_ENDIAN); /*  X86是小端*/
        return buffer.getLong();
    }

    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    public static byte int2OneByte(int num) {
        return (byte) (num & 0x000000ff);
    }

    public static int oneByte2Int(byte byteNum) {
        return byteNum > 0 ? byteNum : (128 + (128 + byteNum));
    }
}
