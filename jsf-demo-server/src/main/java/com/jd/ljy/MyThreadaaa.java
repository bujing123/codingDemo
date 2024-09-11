package com.jd.ljy;

import java.util.concurrent.*;

public class MyThreadaaa {

//    public static void main(String[] args) throws InterruptedException {
//        MyThread myThread = new MyThread();
//        myThread.start();
//
//        MyRunnable myRunnable = new MyRunnable();
//        Thread myRun = new Thread(myRunnable);
//        myRun.run();
//
//        MyCallable myCallable = new MyCallable();
//        myCallable.setMsg("aaaaa");
//        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
//        Thread threadFuture = new Thread(futureTask);
//        threadFuture.start();
//        try {
//            Integer integer = futureTask.get();
//            System.out.println(integer);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println("执行完毕");
//    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        for (int i = 0; i < 5; i++) {
            Task task = new Task(i);
            threadPoolExecutor.execute(task);

            System.out.println(Thread.currentThread().getName() + " 线程池中线程数目：" + threadPoolExecutor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    threadPoolExecutor.getQueue().size() + "，已执行完的任务数目：" + threadPoolExecutor.getCompletedTaskCount());
        }

        threadPoolExecutor.shutdown();
    }
}


class MyThread extends Thread{

    public void run(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThread正在执行");
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyRunnable 正在执行");
    }
}

class MyCallable implements Callable<Integer>{

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private  String msg;

    @Override
    public Integer call() throws Exception {
        System.out.println("MyCallable正在执行" + msg);
        return 1;
    }
}


class Task implements Runnable {

    private int taskNum;

    public Task(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在执行task " + taskNum);
        try {
            "sss".split(".");
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " task " + taskNum + "执行完毕");
    }

}