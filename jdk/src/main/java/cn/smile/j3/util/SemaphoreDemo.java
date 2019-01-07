package cn.smile.j3.util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Smile on 2018/11/28.
 */
public class SemaphoreDemo {

    private static class MyThread extends Thread {
        private Semaphore semaphore;
        public MyThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    long tId = this.getId();
                    System.out.println("Thread " + tId + " acquire lock ... ");
                    semaphore.acquire(1);
                    /*semaphore.tryAcquire(1, 1000, TimeUnit.MILLISECONDS);*/
                    System.out.println("Thread " + tId + " acquired! :) ... ");
                    Thread.sleep(1000);
                    semaphore.release(1);
                    System.out.println("Thread " + tId + " released. ");


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(1, true);
        new MyThread(semaphore).start();
        new MyThread(semaphore).start();
        new MyThread(semaphore).start();
    }
}
