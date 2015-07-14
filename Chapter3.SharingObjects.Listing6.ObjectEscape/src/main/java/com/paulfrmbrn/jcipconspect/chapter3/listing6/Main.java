package com.paulfrmbrn.jcipconspect.chapter3.listing6;

import java.util.HashSet;
import java.util.Set;

/**
 * BAD
 * Allowing internal mutable state to escape
 *
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, world!");

        Set<Object> object = new HashSet<>();

    }

}
