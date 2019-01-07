package cn.smile.j1.lang;

/**
 * Created by Smile on 2018/8/4.
 */
public class EmptyString {

    public static void foo(String e) {
        String local = "";
        System.out.println("local==param : " + (local==e));
        System.out.println("local.equals(param) : " + local.equals(e));

    }
    public static void main(String[] args) {
        String empty1 = "";
        String empty2 = "";

        String a = "1";
        String b = new String("1"); //String.format("%d", 1);
        String c = new String("1"); //String.format("%d", 1);

        System.out.println("a = [" + a + "]");
        System.out.println("b = [" + b + "]");
        System.out.println("a==b : " + (a==b));
        System.out.println("a.equals(b) : " + a.equals(b));
        System.out.println("c==b" + c==b);

        System.out.println("=======================");
        System.out.println("empty1==empty2 : " + (empty1==empty2));
        System.out.println("empty1.equals(empty2) : " + empty1.equals(empty2));
        System.out.println("=======================");
        foo(empty1);
    }
}
