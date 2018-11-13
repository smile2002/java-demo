package cn.smile.j1.lang.thread;

import java.lang.Thread;

public class DeadLock1 implements Runnable {

    private String name;
    private CriticalObj prev;
    private CriticalObj self;

    private DeadLock1(String name, CriticalObj prev, CriticalObj self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                System.out.println("get lock of " + prev.name);
                synchronized (self) {
                    System.out.println(name);
                    count--;
                    self.notify();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static class CriticalObj {
        public String name;
        public CriticalObj(String name) {
            this.name = name;
        }
    }
    public static void main(String[] args) throws Exception {
        CriticalObj a = new CriticalObj("A");
        CriticalObj b = new CriticalObj("B");
        CriticalObj c = new CriticalObj("C");


        DeadLock1 pa = new DeadLock1("A", c, a);
        DeadLock1 pb = new DeadLock1("B", a, b);
        DeadLock1 pc = new DeadLock1("C", b, c);


        new Thread(pa).start();
        new Thread(pb).start();
        new Thread(pc).start();
    }
}