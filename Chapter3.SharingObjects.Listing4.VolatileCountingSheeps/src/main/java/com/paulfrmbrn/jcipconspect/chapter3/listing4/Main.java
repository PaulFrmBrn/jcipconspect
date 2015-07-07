package com.paulfrmbrn.jcipconspect.chapter3.listing4;

/**
 *
 * Counting sheep
 *
 * @author paulfrmbrn
 */
public class Main {

    public static volatile boolean asleep = false;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello, world!");

        new Thread(new Runnable() {
            public void run() {
                int sheepCounter = 0;
                while (!asleep) {
                    countSomeSheep(++sheepCounter);
                }
            }
        }).start();

        Thread.sleep(1);

        asleep = true;

    }

    public static void countSomeSheep(int sheepNumber) {
        System.out.println("Number of this sheep is: " + sheepNumber);
    }

}
