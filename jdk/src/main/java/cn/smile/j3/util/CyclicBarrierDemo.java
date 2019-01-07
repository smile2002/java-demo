package cn.smile.j3.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Smile on 2018/11/28.
 */
public class CyclicBarrierDemo {

    private static class MyThread extends Thread {
        private CyclicBarrier barrier;

        MyThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            System.out.println("Thread " + this.getId() + " try to await ,,,");
            System.out.println("NumberWaiting : " + barrier.getNumberWaiting());
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                System.out.println("Broken Barrier!");
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            }
            System.out.println("Thread " + this.getId() + " waked from await()!");
            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + this.getId() + " finished!");
        }
    }
    public static void main(String[] args) {

        System.out.println("main thread started!");

        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread " + Thread.currentThread().getId() + " barrier.run() started ...");
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("all arrived!");
            }
        });

        System.out.println("NumberWaiting : " + barrier.getNumberWaiting());


        for (int i=0; i<5; i++) {
            new MyThread(barrier).start();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            e.printStackTrace();
        }
        //System.out.println("NumberWaiting : " + barrier.getNumberWaiting());
        System.out.println("main thread " + Thread.currentThread().getId() + " OK!");
    }
}
