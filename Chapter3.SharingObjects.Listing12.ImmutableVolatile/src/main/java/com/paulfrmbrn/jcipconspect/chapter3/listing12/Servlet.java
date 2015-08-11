package com.paulfrmbrn.jcipconspect.chapter3.listing12;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * NORM
 * Immutable holder for caching a number and its factors
 *
 * @author paulfrmbrn
 */
public class Servlet extends HttpServlet {

    // cache object is immutable
    // and volatile provides visibility between threads
    // if new cache object will be created in thread A, while old cache object is processing in thread B,
    // there will be no problem - cache objects in both threads are different and immutable and new thread C
    // will see new cache object
    private volatile OneCacheValue cache = new OneCacheValue(
            BigInteger.valueOf(1l),
            new BigInteger[]{
                    BigInteger.valueOf(1l)
            }
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String numberAsString = request.getParameter("number");
        BigInteger number = new BigInteger(numberAsString);

        BigInteger[] factors = cache.getLastFactors(number);

        if (factors == null) {
            System.out.println("_no_ cahce hit - new calculations");
            factors = getFactors(number);
            cache = new OneCacheValue(number,factors);
        } else {
            System.out.println("cache hit - no calculations");
        }

        response.setStatus(200);
        response.getWriter().write("<html>\n" +
                "<body>\n" +
                "<h2>Hello from Servlet</h2>\n" +
                "<h2>number = " + number + "</h2>\n" +
                "<h2>factors = " + Arrays.asList(factors) + "</h2>\n" +
                "</body>\n" +
                "</html>");

    }

    // this is just stub method
    private BigInteger[] getFactors(BigInteger number) {
        return new BigInteger[] {BigInteger.valueOf(0l),BigInteger.valueOf(1l),BigInteger.valueOf(2l)};
    }

}
