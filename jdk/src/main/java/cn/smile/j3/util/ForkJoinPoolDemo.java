package cn.smile.j3.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * ForkJoinPool：任务拆解，可以以递归方式提交到同一个ForkJoinPool中
 */
public class ForkJoinPoolDemo {
    public static void main( String[] args )
    {
        java.util.concurrent.ForkJoinPool pool = new ForkJoinPool(1000);


        for (int i=0; i<10; i++) {
            /** 提交1个主任务到 ForkJoinPool **/
            NotifyTask task = new NotifyTask(0, -1, null, null);
            pool.submit(task);

            int interval = 5;
            while(true) {
                if (task.isDone()) {
                    break;
                }
                try {
                    Thread.sleep(interval);
                    //System.out.println("not ok...");
                } catch (InterruptedException e) {

                }
                if (interval < 200) interval *= 1.5;
            }
            System.out.println("============= all done ==============");
        }
        pool.shutdown();

    }

    private static class NotifyTask extends RecursiveAction {

        public int task_tp;
        public int id;
        public String url;
        public String data;


        public NotifyTask(int task_tp, int id, String url, String data) {
            super();
            this.task_tp = task_tp;
            this.id = id;
            this.url = url;
            this.data = data;
        }

        @Override
        protected void compute() {

            /** task_tp=0：主任务，会拆解成900个子任务 **/
            if (this.task_tp == 0) {
                System.out.println("main task start!");
                List<NotifyTask> tasks = new ArrayList<>();
                // Read Database
                for (int i = 0; i < 900; i++) {
                    NotifyTask t = new NotifyTask(1, i + 1, "http://avc", "liefsaf");
                    tasks.add(t);
                }

                invokeAll(tasks);
                System.out.println("main task done!");
            } else {
                /** 子任务：每个子任务模拟耗时1秒钟 **/
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("task[%d] done!\n", this.id);
            }
        }
    }
}
