package com.paulfrmbrn.jcipconspect.chapter4.listing41.figure1;

/**
 *
 * Visibility guarantees for synchronization
 *
 * @author paulfrmbrn
 */
public class Main {

    public static volatile boolean done = false;

    public static void main(String[] args) throws InterruptedException {

        final SomeClass anObject = new SomeClass(0,0);

        Thread threadA = new Thread(new Runnable() {
            public void run() {
                int i = 1;
                while (!done) {
                    anObject.setY(++i);
                    synchronized (this) {
                        anObject.setX(i + 10);
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            public void run() {
                int j = 0;
                while (!done) {
                    int localX;
                    synchronized (this) {
                        localX = anObject.getX();
                    }
                    int localY = anObject.getY();

                    System.out.println("#" + ++j + " x = " + localX);
                    System.out.println("#" + j + " y = " + localY);
                }
            }
        });

        threadA.start();
        threadB.start();

        Thread.sleep(3);
        done = true;


    }

}

class SomeClass {
    public int y;
    public int x;

    public SomeClass(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
