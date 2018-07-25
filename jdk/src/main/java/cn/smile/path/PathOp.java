package cn.smile.path;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Smile on 2018/7/25.
 */
public class PathOp {
    public static void main(String[] args) {

        Path path = Paths.get("C:\\Program Files\\Java");
        System.out.println("Path               : " + path);
        System.out.println("Path.getParent     : " + path.getParent());
        System.out.println("Path.getFileName   : " + path.getFileName());
        System.out.println("Path.getName(0)    : " + path.getName(0));
        System.out.println("Path.getName(1)    : " + path.getName(1));
        System.out.println("Path.getRoot       : " + path.getRoot());
        System.out.println("Path.getFileSystem : " + path.getFileSystem().getRootDirectories());
        System.out.println("Path.resolve       : " + path.resolve("jre1.8.0_161\\bin"));
        System.out.println("Path.resolveSibling: " + path.resolveSibling("brother\\bin"));

        File file1 = path.resolve("jre1.8.0_161\\bin").toFile();
        File file2 = path.resolveSibling("brother\\bin").toFile();
        System.out.println("file1.exists = " + file1.exists());
        System.out.println("file2.exists = " + file2.exists());

    }
}
