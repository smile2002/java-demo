package cn.smile.j1.lang;

import java.util.LinkedList;

/**
 * Created by Smile on 2019/1/15.
 */
public class ThreadSyncDemo {

    private static final int MAX_SIZE = 100;
    private static LinkedList<Object> list = new LinkedList<Object>();

    static Runnable produce = new Runnable() {
        @Override
        public void run() {
            Long tId = Thread.currentThread().getId();

            while (true) {
                //模拟生产耗时（add前）
                try { Thread.sleep(250); } catch (InterruptedException e) { }

                synchronized (list) {
                    while (list.size() + 1 > MAX_SIZE) {
                        try {
                            System.out.println("Producer wait ... ");
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Producer " + tId + " back ... !");
                    list.add(new Object());
                    System.out.println("Producer " + tId + " Produce one product! " + list.size());
                    list.notifyAll(); //通知其他被阻塞的线程
                }
            }
        }
    };

    static Runnable consume = new Runnable() {
        @Override
        public void run() {
            Long tId = Thread.currentThread().getId();
            while (true) {
                synchronized (list) {
                    while (list.size() < 1) {
                        try {
                            System.out.println("Consumer wait ... ");
                            list.wait();
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Consumer " + tId + " back ... !");
                    list.remove();
                    System.out.println("Consumer " + tId + " Consume one product! " + list.size());
                    list.notifyAll(); //通知其他被阻塞的线程
                }
                //模拟处理耗时（pop 后）
                try { Thread.sleep(500); } catch (InterruptedException e) { }
            }
        }
    };

    public static void main(String args[]) {
        new Thread(produce).start();
        new Thread(consume).start();
        new Thread(consume).start();


        while (true) {
            try { Thread.sleep(1000); } catch (InterruptedException e) { System.out.println("main"); }
        }
    }
}
