package com.paulfrmbrn.jcipconspect.chapter3.listing8;

/**
 * Using a factory method to prevent the this reference from escaping during construction
 *
 * @author paulfrmbrn
 */
public class Application {

    public static void main(String[] args) {

        System.out.println("Hello, World!");

        EventSource source = new EventSource();
        SafeListener.newInstance(source);

    }

}

class SafeListener {

    private final EventListener listener;

    private SafeListener() { // private constructor. No new thread started in it, or other *this* escape
        listener = new EventListener() { //
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        };

        someOtherConstruction();
        
    }

    private void someOtherConstruction() {
        System.out.println("post construction");
    }

    private void doSomething(Event e) {

    }

    public static SafeListener newInstance(EventSource source) { // public fabric method

        // creating an object
        SafeListener safeListener = new SafeListener();

        // passing *this* reference of the SafeListener to the EventSource ONLY AFTER it was completely constructed
        source.registerListener(safeListener.listener);

        return safeListener;
    }

}

class Event {
}

abstract class EventListener {
    abstract public void onEvent(Event e);
}

class EventSource {

    public void registerListener(EventListener eventListener) {
        System.out.println("listener registered: " + eventListener);
    }
}
