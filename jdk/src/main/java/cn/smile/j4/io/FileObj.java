package cn.smile.j4.io;

import java.io.*;
import java.nio.file.Path;

/**
 * Created by Smile on 2018/10/24.
 */



public class FileObj {

    static void FileOps() {
        File file = new File("D:\\text.txt");

        /* 创建文件 */
        try {
            /* 仅当文件不存在时，创建文件并返回true，否则返false */
            boolean created = file.createNewFile();
            System.out.println("createNewFile()   = " + created);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 文件属性 */
        System.out.println("file.exists       = " + file.exists());        /* true */
        System.out.println("file.length()     = " + file.length());        /* true */
        System.out.println("");

        System.out.println("file.canExecute   = " + file.canExecute());    /* true */
        System.out.println("file.canRead      = " + file.canRead());       /* true */
        System.out.println("file.canWrite     = " + file.canWrite());      /* true */
        System.out.println("file.isFile       = " + file.isFile());        /* false */
        System.out.println("file.lastModified = " + file.lastModified());  /* 最后修改时间 */
        /* setReadOnly()  setWritable() setReadable() setExecutable() */
        System.out.println("");

        System.out.println("getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("getpath()         = " + file.getPath());
        System.out.println("getName()         = " + file.getName());
        System.out.println("");

        System.out.println("getFreeSpace()     = " + file.getFreeSpace());  /* getTotalSpace() getUsableSpace() */
        System.out.println("");

        Path path = file.toPath();

        /* 改名 */
        /*File renamedFile = new File("D:\\abc.txt");
        int result = file.renameTo(renamedFile);*/

        /* 删除文件 */
        /*boolean deleted = file.delete();
        System.out.println("delete()         = " + deleted);*/
    }

    static void DirOps() {
        File file = new File("D:\\MMM");

        System.out.println("file.isDirectory  = " + file.isDirectory());   /* true */

        boolean result = file.mkdir();
        System.out.println("file.mkdir()      = " + result);   /* true */

        System.out.println(file.getName());   /* true */
        String sub[] = file.list();
        if (sub != null) {
            for (String name : sub) {
                System.out.println("   |- " + name);
            }
        }
        /* 获取File对象数组  listFiles()  listRoots()*/
    }

    static void WriteNonExistFile() {
        File file = new File("D:\\text.txt");
        InputStream in = null;
        OutputStream out = null;

        try {
            /** 如果文件不存在，创建输入流会异常 **/
            //in = new FileInputStream(file);

            /** 如果文件不存在，创建输出流会自动创建文件 **/
            out = new FileOutputStream(file, true);
            out.write("Hello File!\r\n".getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[]) {
        //FileOps();
        //DirOps();
        WriteNonExistFile();
    }
}
