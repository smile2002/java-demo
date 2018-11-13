package cn.smile.j1.lang.thread;

import java.util.ArrayList;
import java.util.concurrent.*;


/**
 * 线程池：ThreadPoolExecutor
 * 线程安全队列：BlockingQueue
 * 异步任务：FutureTask
 */
public class FutureTask {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1000,1000,1000, TimeUnit.SECONDS, queue);

        ArrayList<NotifyTask> tasks = new ArrayList<>();
        for(int i=0; i<1000; i++) {
            final int id = i;
            NotifyTask notifyTask = new NotifyTask(id, new Callable() {
                @Override
                public Object call() throws Exception {
                    try{
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("task [%d] done!\n", id);
                    return new Integer(0);
                }
            });
            tasks.add(notifyTask);
            threadPool.submit(notifyTask);
            System.out.printf("task [%d] added...\n", id);
        }

        int successCount = 0;
        for (int i=0; i<1000; i++) {
            NotifyTask n = tasks.get(i);
            try {
                Integer result = (Integer) (n.get());
                if (result.intValue() == 0) successCount++;
                System.out.printf("task [%d] : result = %d\n", n.id, result.intValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Total successful task: %d\n", successCount);

        threadPool.shutdown();
    }


    private static class NotifyTask<Integer> extends java.util.concurrent.FutureTask<Integer> {
        public int id;
        public NotifyTask(int id, Callable<Integer> callable) {
            super(callable);
            this.id = id;
        }
    }
}
