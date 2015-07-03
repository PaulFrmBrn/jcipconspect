package com.paulfrmbrn.jcipconspect.chapter3.page34;

/**
 * @author paul
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ThreadRunner extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println("number = " + number);
        }
    }

    public static void main(String[] args) {

        System.out.println("Hello, world");
        new ThreadRunner().start();
        number = 42;
        ready = true;


    }

}
