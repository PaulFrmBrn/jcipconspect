package com.paulfrmbrn.jcipconspect.chapter3.listing14;

/**
 * BAD
 * Publishing an object without adequate synchtonization
 *
 * @author paulfrmbrn
 */
public class Application {

    public Holder holder; // unsafe publishing by simple creating public reference

    public void initialize () {
        holder = new Holder(42); // another thread can observe a partially constructed object
    }

    public int getHolderValue() {
        return holder.getValue();
    }

    public static void main(String[] args) {

        System.out.println("Hello, World!");

        Application application = new Application();
        application.initialize();
        System.out.println("holder value = " + application.getHolderValue());

    }

}

class Holder {

    private int value;

    public Holder(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
