package com.paulfrmbrn.jcipconspect.chapter3.listing6;

/**
 * BAD
 * Allowing internal mutable state to escape
 *
 * @author paulfrmbrn
 */
public class Main {

    private String[] states = new String[]{"AK", "AL"}; // while this object was defined private

    public String[] getStates() { // this method made states[] efffectively public. This is object escape
        return states;
    }

    public static void main(String[] args) {

        System.out.println("Hello, world!");

        Main main = new Main();
        main.getStates();


    }

}
