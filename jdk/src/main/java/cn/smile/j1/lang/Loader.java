package cn.smile.j1.lang;

/**
 * Created by Smile on 2018/7/25.
 */
public class Loader {

    public static void main(String[] args) {

        /*System.out.println("sun.boot.class.j5nio = " + System.getProperty("sun.boot.class.j5nio"));
        System.out.println("java.ext.dirs       = " + System.getProperty("java.ext.dirs"));
        System.out.println("java.class.j5nio     = " + System.getProperty("java.class.j5nio"));*/

        /**
         * 应用的类加载器：
         * sun.misc.Launcher$AppClassLoader extends URLClassLoader (in rt.jar)
         * int.class.getClassLoader() 会抛NullPointerException异常
         */
        ClassLoader loader = Loader.class.getClassLoader();
        System.out.println("ClassLoader is:" + loader.toString());

        /**
         * AppClassLoader 的父加载器：
         * sun.misc.Launcher$ExtClassLoader extends URLClassLoader
         */
        System.out.println("ClassLoader\'s parent is:" + loader.getParent().toString());

    }
}