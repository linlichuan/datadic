package com.llc.springcloud.dbtool.test;

import java.util.concurrent.TimeUnit;

public class test {

    final static Object lock1 = new Object();
    final static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            test t = new test();
            t.fun();
        }).start();
        new Thread(()->{
            test t = new test();
            t.fun2();
        }).start();
    }

    public void fun(){
        synchronized (lock1){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("fun1 lock1");
                synchronized (lock2){
                    System.out.println("fun1 lock2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void fun2(){
        synchronized(lock2){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("fun2 lock2");
                synchronized (lock1){
                    System.out.println("func2 lock1");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
