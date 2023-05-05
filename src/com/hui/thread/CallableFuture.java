package com.hui.thread;

import java.util.concurrent.*;

public class CallableFuture {

    //使用 Callable+Future 获取执行结果
    public static void main(String[] args) {
        // 创建可缓存的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("======主线程执行任务=====");
        try {
            System.out.println("task运行结果:"+result.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("======所有任务执行完毕=====");

    }
}

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("======子线程在进行计算=====");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}
