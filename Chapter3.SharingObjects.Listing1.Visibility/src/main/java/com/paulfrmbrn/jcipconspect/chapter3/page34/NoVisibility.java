package com.paulfrmbrn.jcipconspect.chapter3.page34;

/**
 * BAD
 * Sharing variables without synchronization
 *
 * @author paulfrmbrn
 */
public class NoVisibility {

    private static boolean ready; // no synchronization
    private static int number; // no synchronization

    private static class ThreadRunner extends Thread {
        @Override
        public void run() {
            while (!ready) { // can loop forever, because write in 27 is not synchronized
                Thread.yield();
            }
            System.out.println("number = " + number); // can print 42, because write in 27 is not synchronized
            // due to reordering
        }
    }

    public static void main(String[] args) {

        new ThreadRunner().start();
        number = 42;
        ready = true;


    }

}
