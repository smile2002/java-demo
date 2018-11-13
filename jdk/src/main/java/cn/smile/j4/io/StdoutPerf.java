package cn.smile.j4.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Smile on 2018/10/23.
 */
public class StdoutPerf {
    public static void main(String args[]) {
        Thread workers[] = new Thread[1000];

        Date start = new Date();


        for (int i = 0; i< 1000; i++) {
            workers[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j<1500; j++) {
                        System.out.println("This is log1 This is log1 This is log1 ");
                        System.out.println("This is log2 This is log2 This is log2 ");
                        System.out.println("This is log3 This is log3 This is log3 ");
                    }
                }
            });
        }
        for (int i = 0; i< 1000; i++) {
            workers[i].start();
        }

        for (int i = 0; i< 1000; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException e) {
                System.out.println("join interrupted!");
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");

        File file = new File("end.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Date end = new Date();

            OutputStream os = new FileOutputStream(file);
            os.write(sdf.format(start).getBytes());
            os.write("\r\n".getBytes());
            os.write(sdf.format(end).getBytes());
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
