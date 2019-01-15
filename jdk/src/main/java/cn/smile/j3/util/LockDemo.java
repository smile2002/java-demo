package cn.smile.j3.util;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Smile on 2019/1/15.
 */
public class LockDemo {
    private static final int MAX_SIZE = 100;

    static Lock lock = new ReentrantLock();
    //static Lock lock = new ReentrantLock(true);   // 公平锁
    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();
    private static LinkedList<Object> list = new LinkedList<Object>();

    static Runnable produce = new Runnable() {
        @Override
        public void run() {
            Long tId = Thread.currentThread().getId();
            while (true) {
                //模拟生产耗时（add前）
                try { Thread.sleep(200); } catch (InterruptedException e) { }
                lock.lock();
                while (list.size() + 1 > MAX_SIZE) {
                    condition1.awaitUninterruptibly();   //实际很少用不可中断的版本
                }
                list.add(new Object());
                System.out.println("Producer " + tId + " Produce one product! " + list.size());
                condition2.signalAll();
                lock.unlock();
            }
        }
    };

    static Runnable consume = new Runnable() {
        @Override
        public void run() {
            Long tId = Thread.currentThread().getId();
            while (true) {
                lock.lock();
                while (list.size() < 1) {
                    condition2.awaitUninterruptibly();
                }
                list.remove();
                System.out.println("    Consumer " + tId + " Consume one product! " + list.size());
                condition1.signalAll();
                lock.unlock();

                //模拟处理耗时
                try { Thread.sleep(500); } catch (InterruptedException e) { }
            }
        }
    };



    public static void main(String args[]) {
        new Thread(produce).start();
        new Thread(consume).start();
        new Thread(consume).start();
    }
}
