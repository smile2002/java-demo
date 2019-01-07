package cn.smile.j1.lang;

import java.lang.annotation.Annotation;

/**
 * Created by Smile on 2018/11/16.
 */
public class Anno {

    public @interface MyClass {
        String alias() default "default-name";
    }

    @MyClass(alias = "Just for test")
    public static class Foo {
        public String attr1;
        public String attr2;
    }

    public static void main(String[] args) {
        Foo foo = new Foo();

        Class<?> clazz = foo.getClass();

        System.out.println(Foo.class.getAnnotations().length);

    }
}
