package cn.smile.lang;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Smile on 2018/7/25.
 */
public class MyLoader {

    public static void main(String[] args) throws Exception {
        ClassLoader myLoader =   new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException{
                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    } else {
                        byte[] b = new byte[is.available()];
                        is.read(b);
                        return defineClass(name, b, 0, b.length);
                    }
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Class<?> clazz = myLoader.loadClass("cn.smile.lang.MyLoader");
        Object obj = clazz.newInstance();
        System.out.println("object class : " + obj.getClass());
        System.out.println("object ClassLoader : " + obj.getClass().getClassLoader());
        System.out.println("object ClassLoader's Parent : " + obj.getClass().getClassLoader().getParent());
        System.out.println("object instance of MultiLoadser : " + Boolean.valueOf(obj instanceof MyLoader));
    }
}
