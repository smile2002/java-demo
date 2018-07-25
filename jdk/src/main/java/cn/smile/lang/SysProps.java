package cn.smile.lang;

import java.io.PrintStream;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Smile on 2018/7/25.
 */
public class SysProps {
    public static void main(String[] args) {

        /**
         *
         sun.java.command = com.intellij.rt.execution.application.AppMain cn.smile.lang.SysProps
         sun.java.launcher = SUN_STANDARD
         sun.boot.class.path = 启动jar包（如rt.jar等，绝对路径）
         sun.boot.library.path = C:\Program Files\Java\jdk1.8.0_161\jre\bin
         sun.arch.data.model = 64
         sun.cpu.isalist = amd64
         sun.cpu.endian = little
         sun.desktop = windows

         java.home = C:\Program Files\Java\jdk1.8.0_161\jre
         java.class.version = 52.0
         java.ext.dirs = 扩展类路径
         java.class.path = (CLASSPATH)
         java.io.tmpdir = C:\Users\Smile\AppData\Local\Temp\
         java.library.path =
         java.runtime.name / version
         java.specification.name / vendor / version
         java.vendor = Oracle Corporation

         java.version = 1.8.0_161
         java.vm.info = mixed mode
         java.vm.name / vendor / version
         java.vm.specification.name / vendor / version

         os.arch = amd64
         os.name = Windows 10
         os.version = 10.0
         line.separator = (\n)
         path.separator = ;

         user.country = CN
         user.dir = D:\IDEAProjects\java-demo
         user.home = C:\Users\Smile
         user.language = zh
         user.name = XXX
         */
        Properties props = System.getProperties();
        /** 简单方法： props.list(new PrintStream(System.out)); **/
        Set<Object> keySet = props.keySet();
        for(Object key : keySet) {
            System.out.println(key + " = " + props.get(key));
        }

    }
}
