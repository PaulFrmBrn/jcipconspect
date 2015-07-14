package com.paulfrmbrn.jcipconspect.chapter4.listing41.figure1;

/**
 * Visibility guarantees for synchronization
 *
 * @see http://stackoverflow.com/questions/14714847/java-concurrent-visibility-of-primitive-array-writes
 * for better examples and explanations
 *
 * @author paulfrmbrn
 */
public class Main {

    volatile static boolean done = false;

    public static void main(String[] args) throws InterruptedException {

        Holder y = new Holder(-1);
        Holder x = new Holder(-2);

        Thread writer = new Thread(() -> {
            y.set(1);
            //synchronized (x) {
                x.set(2);
            //}
        });

        Thread reader = new Thread(() -> {
            int localX = -3;
            int localY = -4;

            while (!done) {

                //synchronized (x) {
                    localX = x.get();
                //}
                localY = y.get();
            }
            System.out.println(" x = " + localX);
            System.out.println(" y = " + localY);
        });

        reader.start();
        writer.start();
        Thread.sleep(1000);
        done = true;


    }

}

class Holder {
    public int x;

    public Holder(int x) {
        this.x = x;
    }

    public int get() {
        return x;
    }

    public void set(int x) {
        this.x = x;
    }
}
