package cn.smile.j1.lang;

/**
 * Created by Smile on 2018/8/4.
 */
public class EmptyString {
    public static void main(String[] args) {
        String a = "1";
        String b = String.format("%d", 1);
        String c = String.format("%d", 1);

        System.out.println("a = [" + a + "]");
        System.out.println("b = [" + b + "]");
        System.out.println("a==b : " + (a==b));
        System.out.println("a.equals(b) : " + a.equals(b));
        System.out.println(c==b);
    }
}
