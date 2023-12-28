package com.zergatstage.seminars.s06;

import org.apache.commons.math3.random.RandomAdaptor;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int trials = 1000;  // # trials
        int wins = 0;                            // # times you win by switching
        RandomGenerator randomGenerator = new Well19937c();
        RandomAdaptor rnd = new RandomAdaptor(randomGenerator);
        // repeat experiment trials times
        for (int i = 0; i < trials; i++) {

            // host hides prize behind 1 of 3 doors uniformly at random
            int prize  = (int) (3 * rnd.nextDouble());

            // contestant selects 1 of 3 doors uniformly at random
            int choice = (int) (3 * rnd.nextDouble());

            // at random, host reveals an unchosen door not containing prize
            int reveal;
            do {
                reveal = (int) (3 * rnd.nextDouble());
            } while ((reveal == choice) || (reveal == prize));

            // hack to compute the remaining door which contestent switches to
            int other = 0 + 1 + 2 - reveal - choice;

            // switching leads to a win
            if (other == prize) wins++;
        }

        // avoid integer division
        System.out.println("Fraction of games won = " + 1.0 * wins / trials);
    }

}
