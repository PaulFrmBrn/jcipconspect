package com.paulfrmbrn.jcipconspect.chapter3.listing2;

/**
 * BAD
 * Non-thread-safe mutable integer holder
 *
 * @author paulfrmbrn
 */
//NonThreadSafe
class MutableInteger {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello, world!");

        final MutableInteger mutableInteger = new MutableInteger();

        new Thread(new Runnable() {
            public void run() {
                mutableInteger.setValue(42);
            }
        }).start();
        System.out.println("mutableInteger.getValue() = " + mutableInteger.getValue()); // can return 0, even if new thread
        // has already made write in 34
    }


}
