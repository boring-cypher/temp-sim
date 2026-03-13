package sims.util;

public class UI {
    public static String makeBar(int value, int maxValue, int width) {
        value = Math.max(0, Math.min(value, maxValue));
        int filled = (int) Math.round((value / (double) maxValue) * width);
        return "[" + "█".repeat(filled) + " ".repeat(width - filled) + "]";
    }

    public static void printStatus(int energyLeft, int maxEnergy, int happiness, int social, int knowledge, double balance) {
        int energyPercent = (int) Math.round((energyLeft / (double) maxEnergy) * 100);

        System.out.println("\n================== Status ==================");
        System.out.println("Energy:    " + makeBar(energyLeft, maxEnergy, 10) + " " + energyLeft + "/" + maxEnergy + " (" + energyPercent + "%)");
        System.out.println("Happiness: " + makeBar(happiness, 100, 10) + " " + happiness + "%");
        System.out.println("Social:    " + makeBar(social, 100, 10) + " " + social + "%");
        System.out.println("Knowledge: " + makeBar(knowledge, 100, 10) + " " + knowledge + "%");
        System.out.println("Balance:   $" + balance);

        if (happiness <= 30) {
            System.out.println("Warning: Happiness is low. Please consider increasing it.");
        }
        if (social <= 30) {
            System.out.println("Warning: Social is low. Please consider increasing it.");
        }

        System.out.println("============================================\n");
    }

    public static void printTurnHeader(int turnNo, int energyLeft) {
        System.out.println("\n======================================");
        System.out.println("TURN " + turnNo + " (Energy: " + energyLeft + ")");
        System.out.println("======================================");
    }

}
