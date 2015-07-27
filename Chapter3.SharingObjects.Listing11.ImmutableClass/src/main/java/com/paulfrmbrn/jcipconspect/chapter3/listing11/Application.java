package com.paulfrmbrn.jcipconspect.chapter3.listing11;

import java.util.HashSet;
import java.util.Set;

/**
 * Immutable class built out of mutable underlying objects
 *
 * @author paulfrmbrn
 */
public class Application {

    public static void main(String[] args) {

        System.out.println("Hello, World!");

        ThreeStooges stooges = new ThreeStooges();
        System.out.println("Moe = " + stooges.isStooge("Moe"));
        System.out.println("Jane = " + stooges.isStooge("Jane"));

    }

}

final class ThreeStooges {

    private final Set<String> stooges = new HashSet<>(); // stooges - is final BUT mutable

    public ThreeStooges() { // BUT is unreachable outside the constructor by design
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name); // but properly constructed (no escape reference)
    }
}
