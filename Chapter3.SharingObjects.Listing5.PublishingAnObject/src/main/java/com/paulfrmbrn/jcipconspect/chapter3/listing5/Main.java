package com.paulfrmbrn.jcipconspect.chapter3.listing5;

import java.util.HashSet;
import java.util.Set;

/**
 * EXAMPLE
 * Publishing an object
 *
 * @author paulfrmbrn
 */
public class Main {

    public static Set<Object> knownSecret;

    public void initialize () {
        knownSecret = new HashSet<>();
    }

    public static void main(String[] args) {

        System.out.println("Hello, world");

        Main main = new Main();
        main.initialize(); // initialization
        Set<Object> secretKnown2Everyone = main.knownSecret; // publishing an onject
        System.out.println("secretKnown2Everyone = " + secretKnown2Everyone);

    }

}
