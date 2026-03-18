package sims.Summary;
import sims.event.StoryState;

import sims.character.Character;
import sims.item.Item;
import sims.job.Job;

import java.util.ArrayList;

public class SummaryPage
{
    public static void printSummary(Character character, StoryState storyState) {
        if (character == null) {
            System.out.println("No character summary available.");
            return;
        }

        System.out.println("\n================= Game Summary =================");
        System.out.println("Name:      " + character.getName());
        System.out.println("Gender:    " + character.getGender());
        System.out.println("Happiness: " + character.getHappiness());
        System.out.println("Social:    " + character.getSocial());
        System.out.println("Knowledge: " + character.getKnowledge());
        System.out.println("Balance:   $" + character.getBalance());

        Job job = character.getCurrentJob();
        if (job == null) {
            System.out.println("Job:       None");
        } else {
            System.out.println("Job:       " + job.getName());
        }

        ArrayList<Item> inventory = character.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("Inventory: Empty");
        } else {
            System.out.println("Inventory:");
            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                System.out.println("  " + (i + 1) + ") " + item.getName());
            }
        }

        if (storyState == null || storyState.getProjectRoute() == StoryState.ProjectRoute.UNSET) {
            System.out.println("Route: Undecided");
        } else if (storyState.getProjectRoute() == StoryState.ProjectRoute.CARRY) {
            System.out.println("Route: The One Who Held It Together");
        } else if (storyState.getProjectRoute() == StoryState.ProjectRoute.REPAIR) {
            System.out.println("Route: The One Who Rebuilt the Team");
        } else if (storyState.getProjectRoute() == StoryState.ProjectRoute.ESCALATE) {
            System.out.println("Route: The One Who Drew the Line");
        } else {
            System.out.println("Route: Undecided");
        }

        printEndingReflection(storyState);
        System.out.println("================================================\n");
    }

    private static void printEndingReflection(StoryState storyState) {
        if (storyState == null) {
            return;
        }

        StoryState.ProjectRoute route = storyState.getProjectRoute();

        if (route == StoryState.ProjectRoute.UNSET) {
            System.out.println("\n=== END OF SEMESTER REFLECTION ===\n");
            System.out.println("Route: Undecided\n");
            System.out.println("The semester ended before one defining path fully took shape. Instead of being remembered for a single decision, your journey was made up of small choices, unfinished possibilities, and moments that could have led in many different directions.\n");
            System.out.println("By the end, what stands out is not one dramatic turning point but the uncertainty itself. University rarely resolves neatly, and not every struggle becomes a clear story while you are living through it. Even so, you kept going, learning more about yourself through the process than through any single outcome.\n");
            System.out.println("You leave the semester with questions still open, but also with more experience, more self-awareness, and the sense that your direction is still yours to decide.");
            return;
        }

        if (route == StoryState.ProjectRoute.CARRY) {
            System.out.println("\n=== END OF SEMESTER REFLECTION ===\n");
            System.out.println("Route: The One Who Held It Together\n");
            System.out.println("When the project started to fall apart, you chose to carry the burden quietly instead of letting the team collapse. That decision shaped the rest of your semester. You became the person others could rely on, even when it meant taking on more than your fair share.\n");
            System.out.println("By the end of the semester, your resilience and determination carried you through deadlines, revision, and exams. But this path also revealed how easily you absorb pressure alone. You succeeded by enduring, even when others did not fully see what it cost you.\n");
            System.out.println("You leave the semester stronger, more dependable, and more aware that responsibility can be both a strength and a weight.");
            return;
        }

        if (route == StoryState.ProjectRoute.REPAIR) {
            System.out.println("\n=== END OF SEMESTER REFLECTION ===\n");
            System.out.println("Route: The One Who Rebuilt the Team\n");
            System.out.println("When the project started to break down, you chose to confront the problem directly and repair the workflow. That decision defined the rest of your semester. Instead of carrying everything alone or stepping back completely, you tried to rebuild trust, restore order, and keep the group moving together.\n");
            System.out.println("By the end of the semester, this path showed your patience, communication, and leadership. You handled pressure without losing sight of the people around you. Even when the semester became overwhelming, you proved that stability can come from working through problems rather than avoiding them.\n");
            System.out.println("You leave the semester steadier, more balanced, and more capable of leading under pressure.");
            return;
        }

        if (route == StoryState.ProjectRoute.ESCALATE) {
            System.out.println("\n=== END OF SEMESTER REFLECTION ===\n");
            System.out.println("Route: The One Who Drew the Line\n");
            System.out.println("When the project started falling apart, you chose to escalate the issue professionally rather than carrying it in silence or trying to fix everything informally. That decision shaped the rest of your semester. You made it clear that accountability, structure, and boundaries mattered when the stakes became real.\n");
            System.out.println("By the end of the semester, this path showed your sense of responsibility and professionalism. You protected the bigger outcome of the project and refused to let poor teamwork go unchecked. Even if the route felt colder at times, it proved that difficult decisions are sometimes necessary to move forward.\n");
            System.out.println("You leave the semester more disciplined, more self-protective, and more confident in setting boundaries when pressure rises.");
        }
    }
}
