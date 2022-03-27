package com.zhuang;


import java.util.concurrent.CountDownLatch;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        //创建10个线程执行
        for (int i = 1; i <= 10; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            },i+"").start();

        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"关闭了通道！");

    }
}
