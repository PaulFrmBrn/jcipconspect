package com.paulfrmbrn.jcipconspect.chapter3.listing9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * GOOD
 * Thread Confinement of local primitive and reference variables
 *
 * @author paulfrmbrn
 */
public class Application {



    public static void main(String[] args) {

        System.out.println("Hello, World!");

        Ark ark = new Ark();

        Collection<Animal> candidates = new ArrayList<>();

        candidates.add(new Animal("mouse",false));

        candidates.add(new Animal("cat",true));
        candidates.add(new Animal("cat",false));

        candidates.add(new Animal("dog",true));
        candidates.add(new Animal("dog",false));
        candidates.add(new Animal("dog",false));

        candidates.add(new Animal("mouse",true));
        candidates.add(new Animal("calibre",false));

        int arkSpecies = loadTheArk(ark, candidates);

        System.out.println("ark = " + ark);
        System.out.println("arkSpecies = " + arkSpecies);


    }

    public static int loadTheArk(Ark ark, Collection<Animal> candidates) {

        SortedSet<Animal> animals; // method local reference variable
        int numPairs = 0; // method local primitive variable
        Animal candidate = null;

        // animals - thread confined
        animals = new TreeSet<>(new SpeciesGenderComparartor()); // creating sorted candidates, with no duplicates
        animals.addAll(candidates);

        for (Animal animal : animals) {

            if (candidate == null || !candidate.isPotentialMate(animal)) {
                candidate = animal;
            } else {
                ark.load(new AnimalPair(candidate,animal));
                ++numPairs;
                candidate = null;
            }

        }

        return numPairs;
    }


}

class Animal {

    private final String name;
    private final boolean isMale;

    public Animal(String name, boolean isMale) {
        this.name = name;
        this.isMale = isMale;
    }

    public String getName() {
        return name;
    }

    public boolean isMale() {
        return isMale;
    }

    public boolean isPotentialMate(Animal that) {
        return this.isMale != that.isMale;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", isMale=" + (isMale ? "M" : "W")  +
                '}';
    }
}

class AnimalPair {
    private final Animal male;
    private final Animal female;

    public AnimalPair(Animal male, Animal female) {
        this.male = male;
        this.female = female;
    }

    @Override
    public String toString() {
        return "Pair <" +
                "M=" + male +
                ", W=" + female +
                '>';
    }
}

class Ark {
    private final Collection<AnimalPair> pairs;

    public Ark() {
        this.pairs = new ArrayList<>();
    }

    public void load(AnimalPair pair) {
        pairs.add(pair);
    }

    @Override
    public String toString() {
        return "Ark " +
                "pairs = {" + pairs +
                '}';
    }
}

class SpeciesGenderComparartor implements java.util.Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {

        int result = o1.getName().compareTo(o2.getName());

        if (result == 0) { // if name is the same
            if (o1.isMale() != o2.isMale()) { //  but gender is not the same
                result = (o1.isMale()) ? 1 : -1;
            }
        }
        return result;
    }
}