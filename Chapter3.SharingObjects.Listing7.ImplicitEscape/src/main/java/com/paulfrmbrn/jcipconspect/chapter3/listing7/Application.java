package com.paulfrmbrn.jcipconspect.chapter3.listing7;

/**
 * BAD
 * Implicitly allwoing the this reference to secape
 *
 * @author paulfrmbrn
 */
public class Application {

    public static void main(String[] args) {

        System.out.println("Hello, World!");

        EventSource source = new EventSource();
        new ThisEscape(source);

    }

}

class ThisEscape { // enclosing object

    public ThisEscape(EventSource source) {

        source.registerListener( // EventSource have hidden reference to the ThisEscape object

                new EventListener() { // inner class

                    public void onEvent(Event e) {
                        /*ThisEscape.this.*/doSomething(e); // hidden reference to the enclosing object
                        // implicit publishing of this reference to the enclosing object (ThisEscape)
                        // in anonumouse class (EventListener)

                    }
                }
        );
    }

    private void doSomething(Event e) {

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
