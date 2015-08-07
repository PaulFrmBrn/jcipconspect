package com.paulfrmbrn.jcipconspect.chapter3.listing12;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Immutable holder fpr caching a number and its factors
 *
 * @author paul
 */
public class OneCacheValue {

    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneCacheValue(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        this.lastFactors = Arrays.copyOf(lastFactors,lastFactors.length); // is needed the object to be immutable. defencive copying
    }

    public BigInteger[] getLastFactors(BigInteger currentNumber) {
        if (lastNumber == null || !lastNumber.equals(currentNumber)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length); // is needed the object to be immutable. defencive copying
        }
    }
}
