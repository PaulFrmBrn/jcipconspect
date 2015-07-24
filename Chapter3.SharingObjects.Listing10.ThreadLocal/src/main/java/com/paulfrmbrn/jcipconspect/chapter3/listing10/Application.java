package com.paulfrmbrn.jcipconspect.chapter3.listing10;

import java.util.Random;

/**
 * Using ThreadLocal to ensure thread confinement
 *
 * @author paulfrmbrn
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello, World!");

        System.out.println("connection 0 = " + getConnection()); // a
        new Thread(() -> {System.out.println("connection 1 = " + getConnection());}).start(); // b
        new Thread(() -> {System.out.println("connection 2 = " + getConnection());}).start(); // c
        Thread.sleep(1000L);
        System.out.println("connection 0 = " + getConnection()); // d

        // lines a and d will print the same result, since ThreadLocal connectionHolder.get()
        // returns the same value for the one thread
        // but lines b and c output will differ - connectionHolder is accessed from another thread

    }

    private static ThreadLocal<Integer> connectionHolder = new ThreadLocal<Integer>() {
        @Override
        public Integer initialValue() {
            return new Random().nextInt();
        }
    };

    public static Integer getConnection() {
        return connectionHolder.get();
    }

}
