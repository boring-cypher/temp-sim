package sims.event;

import sims.character.Character;
import sims.job.*;

import java.util.List;

public class EventManager {
    private final List<Events> randomEvents;
    private final List<Events> studyEvents;
    private final List<Events> holidayEvents;
    private final List<Events> examEvents;

    // to call executeChoice
    private final TurnProcessor turnProcessor = new TurnProcessor();

    // new: stores the chosen story branch for this run
    private final StoryState storyState = new StoryState();

    public EventManager() {
        this.randomEvents = RandomEventBuilder.buildRandomEvents();
        this.studyEvents = StudyWeekEventBuilder.buildStudyEvents();
        this.holidayEvents = HolidayEventBuilder.buildHolidayEvents();
        this.examEvents = ExamWeekBuilder.buildExamEvents();
    }

    private Phase getPhase(int turnNo) {
        if (turnNo <= 14) {
            return Phase.STUDY;
        } else if (turnNo <= 16) {
            return Phase.RECESS;
        } else {
            return Phase.EXAM;
        }
    }

    private void printPhaseTransition(Phase previousPhase, Phase currentPhase, Character character) {
        if (previousPhase == null) {
            System.out.println("\n=== " + currentPhase.getDisplayName() + " Begins ===");
        } else if (currentPhase != previousPhase) {
            System.out.println("\n=== " + previousPhase.getDisplayName() + " Completed! ===");
            System.out.println("=== " + currentPhase.getDisplayName() + " Begins ===");

            if (currentPhase == Phase.RECESS) {
                triggerJobSelectionEvent(character);
            }
        }
    }

    private List<Events> getEventsForTurn(Phase currentPhase, int turnNo) {
        List<Events> phaseEvents = switch (currentPhase) {
            case STUDY -> studyEvents;
            case RECESS -> holidayEvents;
            case EXAM -> examEvents;
        };

        return phaseEvents.stream()
                .filter(e -> e.turn() == turnNo)
                .filter(e -> e.requiredProjectRoute() == null
                        || e.requiredProjectRoute() == storyState.getProjectRoute())
                .toList();
    }

    public void mainGameFlow(Character character) {
        final int TOTAL_TURNS = 20;
        final int MAX_ENERGY = 10;

        Phase previousPhase = null;

        for (int currentTurn = 1; currentTurn <= TOTAL_TURNS; currentTurn++) {
            Phase currentPhase = getPhase(currentTurn);
            printPhaseTransition(previousPhase, currentPhase, character);
            previousPhase = currentPhase;

            List<Events> eventsThisTurn = getEventsForTurn(currentPhase, currentTurn);

            if (eventsThisTurn.isEmpty()) {
                System.out.println("No main events found for turn " + currentTurn + ". Skipping...");
                continue;
            }

            turnProcessor.playTurn(
                    character,
                    currentTurn,
                    MAX_ENERGY,
                    eventsThisTurn,
                    currentPhase,
                    randomEvents,
                    storyState
            );
        }

        if (previousPhase != null) {
            System.out.println("\n=== " + previousPhase.getDisplayName() + " Completed! ===");
        }
    }

    private void triggerJobSelectionEvent(Character character) {
        if (character.hasJob()) {
            System.out.println("\nYou already have a job: " + character.getCurrentJob().getName());
            return;
        }

        System.out.println("\n=== Special Event: Holiday Job Hunt ===");
        System.out.println("Recess week has started, and you decide to look for a part-time job.");

        List<Job> availableJobs = List.of(
                new CafeAssistantJob(),
                new RetailPromoterJob(),
                new TutorJob()
        );

        for (int i = 0; i < availableJobs.size(); i++) {
            Job job = availableJobs.get(i);
            System.out.println((i + 1) + ")" + job.getName());
            System.out.println(" " + job.getDesc());
            System.out.println(" Salary: $" + job.getSalary()
                    + " | Shift Energy: " + job.getShiftEnergyCost()
                    + " | Minimum shifts before quitting: " + job.getMinTurnsRequired());
        }

        System.out.println((availableJobs.size() + 1) + ") Do not take a job");
        int choice = TurnProcessor.getUserChoice(availableJobs.size() + 1);

        if (choice == availableJobs.size()) {
            System.out.println("You decided not to take a job this recess.");
            return;
        }

        Job selectedJob = availableJobs.get(choice);
        character.setCurrentJob(selectedJob);
        System.out.println("You get hired as a " + selectedJob.getName() + "!");
    }
}