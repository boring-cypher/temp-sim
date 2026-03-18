package sims.util;

import sims.event.Phase;

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

        // Print per-stat warnings for low values.
        printStatWarnings(happiness, "Happiness");
        printStatWarnings(social, "Social");
        printStatWarnings(knowledge, "Knowledge");

        System.out.println("============================================\n");
    }

    public static void printTurnHeader(int turnNo, int energyLeft) {
        System.out.println("\n======================================");
        System.out.println("TURN " + turnNo + " (Energy: " + energyLeft + ")");
        System.out.println("======================================");
    }

    public static void printBlankLines(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }

    // Emit warning messages based on the given stat value and label.
    private static void printStatWarnings(int value, String label) {
        // Critical range: very low values.
        if (value <= 10) {
            System.out.println("Critical: " + label + " is very low.");
        } else if (value <= 25) { // Warning range: low values, but not critical.
            System.out.println("Warning: " + label + " is low.");
        } else if (value < 50) {
            System.out.println("Warning: " + label + " is moderately low.");
        }
    }

    public static void printWelcome() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              Welcome to Uni Life Simulator!                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("You are a university student navigating the challenges of");
        System.out.println("academic life. Each turn represents a week where you must");
        System.out.println("manage your time and energy wisely.");
        System.out.println();
        System.out.println("Your goal is to graduate successfully while maintaining a");
        System.out.println("balanced lifestyle.");
        System.out.println();
        System.out.println("─── Key Stats ───────────────────────────────────────────────");
        System.out.println("  Knowledge  – gained by studying and attending academic activities");
        System.out.println("  Happiness  – improved through social activities and leisure");
        System.out.println("  Energy     – used to perform actions each turn");
        System.out.println();
        System.out.println("─── Activities ──────────────────────────────────────────────");
        System.out.println("  Study          – increase Knowledge");
        System.out.println("  Attend CCA     – build skills and social connections");
        System.out.println("  Socialise      – spend time with friends");
        System.out.println("  Family         – spend time with family");
        System.out.println("  Shop           – buy items that provide useful stat boosts");
        System.out.println();
        System.out.println("─── Remember ────────────────────────────────────────────────");
        System.out.println("  Each action costs Energy. Choose wisely.");
        System.out.println("  Focusing too much on one area may cause others to suffer.");
        System.out.println("  Balance your academics, social life, and well-being.");
        System.out.println();
        System.out.println("  Good luck, and enjoy your university journey!");
        System.out.println("─────────────────────────────────────────────────────────────");
        System.out.println();
    }

    public static void printPhaseTransition(Phase previousPhase, Phase currentPhase) {
        if (previousPhase == null) {
            System.out.println("\n=== " + currentPhase.getDisplayName() + " Begins ===");
        } else if (currentPhase != previousPhase) {
            System.out.println("\n=== " + previousPhase.getDisplayName() + " Completed! ===");
            System.out.println("=== " + currentPhase.getDisplayName() + " Begins ===");
        }
    }
}
