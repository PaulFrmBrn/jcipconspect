package com.paulfrmbrn.jcipconspect.chapter4.listing41.figure1;

/**
 * Visibility guarantees for synchronization
 *
 * @author paulfrmbrn
 * @see http://stackoverflow.com/questions/14714847/java-concurrent-visibility-of-primitive-array-writes
 * for better examples and explanations
 */
public class Main {

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

            int i = 0;
            while (true) {

                //synchronized (x) {
                    localX = x.get();
                //}
                localY = y.get();

                System.out.println(" x[" + i + "] = " + localX);
                System.out.println(" y[" + i + "] = " + localY);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (++i >= 10) {
                    break;
                }

            }

        });


        reader.start();
        Thread.sleep(3000);
        writer.start();


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

