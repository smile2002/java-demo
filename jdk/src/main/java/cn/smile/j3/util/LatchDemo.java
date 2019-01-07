package cn.smile.j3.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Smile on 2018/11/27.
 */
public class LatchDemo {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(5000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        while(true) {
            boolean done = false;
            try {
                done = latch.await(100, TimeUnit.MILLISECONDS);
                System.out.println("latch.Count = " + latch.getCount());
                if (!done) {
                    System.out.println("      Continue......");
                    continue;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Interrupted!");
            }
            System.out.println("====== Done ======");
            break;
        }
    }
}
