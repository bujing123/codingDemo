package com.jd.leetcode;

import lombok.SneakyThrows;

public class ThreadLocalDemo {
//    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
//
//    public static void main(String[] args) throws InterruptedException {
//        System.out.println(threadLocal);
//        System.out.println(threadLocal.hashCode());
//
//        new Thread(){
//            @SneakyThrows
//            public void run(){
//                threadLocal.set(111);
//                Thread.sleep(100);
//                System.out.println(threadLocal.get());
//                System.out.println(threadLocal);
//                System.out.println(threadLocal.hashCode());
//            }
//        }.start();
//
//        Thread.sleep(50);
//        new Thread(){
//            public void run(){
//                System.out.println(threadLocal.get());
//                System.out.println(threadLocal);
//                System.out.println(threadLocal.hashCode());
//            }
//        }.start();
//    }
    private static final ThreadLocal<MyObject> threadLocal = new ThreadLocal<MyObject>() {
        @Override
        protected MyObject initialValue() {
            return new MyObject();
        }
    };

    public static void main(String[] args) {
        new Thread(() -> {
            MyObject obj = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + ": " + System.identityHashCode(obj));
            System.out.println(threadLocal.hashCode());
        }).start();

        new Thread(() -> {
            MyObject obj = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + ": " + System.identityHashCode(obj));
            System.out.println(threadLocal.hashCode());
        }).start();


    }

    static class MyObject {
        // Some fields and methods
    }

}
