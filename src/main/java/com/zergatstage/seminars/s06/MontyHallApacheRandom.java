package com.zergatstage.seminars.s06;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.HashMap;
import java.util.Map;

public class MontyHallApacheRandom {

    public static void main(String[] args) {
        int totalSimulations = 10000;

        // Map to store results (iteration number -> win by switching)
        Map<Integer, Boolean> resultsMap = new HashMap<>();

        for (int i = 1; i <= totalSimulations; i++) {
            boolean switchWin = simulateMontyHall();
            resultsMap.put(i, switchWin);
        }

        // Print results
        printResults(resultsMap);
    }

    /**
     * Simulate the Monty Hall problem.
     *
     * @return True if the contestant wins by switching, false if the contestant wins by staying.
     */
    private static boolean simulateMontyHall() {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator(new Well19937c());

        // Place the car behind one of the doors randomly
        int carDoor = randomDataGenerator.nextInt(1, 4);

        // Contestant makes initial choice
        int contestantChoice = randomDataGenerator.nextInt(1, 4);

        // Host opens one of the other doors with a goat behind it
        int remainingDoors[] = {1, 2, 3};
        for (int door : remainingDoors) {
            if (door != carDoor && door != contestantChoice) {
                // Host opens the door with a goat
                remainingDoors = new int[]{contestantChoice, door};
                break;
            }
        }

        // Contestant decides whether to switch or stay
        int switchDoor = randomDataGenerator.nextInt(0, 2);
        int finalChoice = switchDoor == 1 ? remainingDoors[1] : contestantChoice;

        // Check if the final choice is the car door
        return finalChoice == carDoor;
    }

    /**
     * Print the results of the Monty Hall simulations.
     *
     * @param resultsMap The map containing the results of each iteration.
     */
    private static void printResults(Map<Integer, Boolean> resultsMap) {
        System.out.println("Results after " + resultsMap.size() + " simulations:");

        // Print each iteration result
        for (Map.Entry<Integer, Boolean> entry : resultsMap.entrySet()) {
            int iteration = entry.getKey();
            boolean switchWin = entry.getValue();
            System.out.println("Iteration " + iteration + ": " + (switchWin ? "Switching wins" : "Staying wins"));
        }

        // Calculate and print the overall win percentages
        long switchWins = resultsMap.values().stream().filter(Boolean::booleanValue).count();
        long stayWins = resultsMap.size() - switchWins;

        double switchWinPercentage = (double) switchWins / resultsMap.size() * 100;
        double stayWinPercentage = (double) stayWins / resultsMap.size() * 100;

        System.out.println("\nOverall Results:");
        System.out.println("Switching wins: " + switchWins + " times (" + switchWinPercentage + "%)");
        System.out.println("Staying wins: " + stayWins + " times (" + stayWinPercentage + "%)");
    }
}
