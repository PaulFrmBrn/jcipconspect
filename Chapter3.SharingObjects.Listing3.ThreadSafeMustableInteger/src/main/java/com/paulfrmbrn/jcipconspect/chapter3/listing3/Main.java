package com.paulfrmbrn.jcipconspect.chapter3.listing3;

/**
 * BAD
 * Thread-safe mutable integer holder
 *
 * @author paulfrmbrn
 */
//ThreadSafe
class SynchronizedInteger {

    //GuardedBy("this")
    private int value;

    synchronized public int getValue() {
        return value;
    }

    synchronized public void setValue(int value) {
        this.value = value;
    }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, world!");

        final SynchronizedInteger synchronizedInteger = new SynchronizedInteger();

        new Thread(new Runnable() {
            public void run() {
                synchronizedInteger.setValue(42);
            }
        }).start();
        System.out.println("value = " + synchronizedInteger.getValue()); // can not return 0, if new thread
        // has already made write in 34. result will be 42

    }


}