package cn.smile.j3.util;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Smile on 2018/12/2.
 */
public class BlkQueProdConsDemo {

    private static final int queueSize = 5;
    private static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(queueSize);
    private static final int produceSpeed = 100;//生产速度(越小越快)
    private static final int consumeSpeed = 110;//消费速度(越小越快)

    public static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    //System.out.println("老板准备炸油条了，架子上还能放：" + (queueSize - queue.size()) + "根油条");
                    queue.put("1根油条");
                    System.out.println("老板生产了 1 根油条，架子上总共：" + queue.size()  + "根油条");
                    Thread.sleep(produceSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //消费者
    public static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    //System.out.println("客户 准备买油条了，架子上还剩" + queue.size() + "根油条");
                    queue.take();
                    System.out.println("    客户 买到1根油条，架子上还剩" + queue.size() + "根油条");
                    Thread.sleep(consumeSpeed);

                    /*System.out.println("B 准备买油条了，架子上还剩" + queue.size() + "根油条");
                    queue.take();
                    System.out.println("B 买到1根油条，架子上还剩" + queue.size() + "根油条");
                    Thread.sleep(consumeSpeed);

                    System.out.println("C 准备买油条了，架子上还剩" + queue.size() + "根油条");
                    queue.take();
                    System.out.println("C 买到1根油条，架子上还剩" + queue.size() + "根油条");
                    Thread.sleep(consumeSpeed);*/
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
        consumer.start();
    }

}
