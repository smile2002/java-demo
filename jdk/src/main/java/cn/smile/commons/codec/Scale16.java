package cn.smile.commons.codec;
import org.apache.commons.codec.binary.Hex;
/**
 * Created by Smile on 2018/12/27.
 */
public class Scale16 {

    public static void main(String[] args) {
        System.out.printf("%X\n",                   0L);
        System.out.printf("%X\n",                  99L);
        System.out.printf("%X\n",                9999L);
        System.out.printf("%X\n",               99999L);
        System.out.printf("%X\n",            99999999L);
        System.out.printf("%X\n",         99999999999L);
        System.out.printf("%X\n",      99999999999999L);
        System.out.printf("%X\n",   99999999999999999L);
        System.out.printf("%X\n", 9223372036854775807L);    }
}
