package sims.event;

import sims.character.Character;
import sims.job.*;
import sims.util.UI;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<Events> randomEvents;
    private final List<Events> studyEvents;
    private final List<Events> holidayEvents;
    private final List<Events> examEvents;

    private final TurnProcessor turnProcessor = new TurnProcessor();
    private final StoryState storyState = new StoryState();

    public EventManager() {
        this.randomEvents = RandomEventBuilder.buildRandomEvents();
        this.studyEvents = StudyWeekEventBuilder.buildStudyEvents();
        this.holidayEvents = HolidayEventBuilder.buildHolidayEvents();
        this.examEvents = ExamWeekBuilder.buildExamEvents();
    }

    private Phase getPhase(int turnNo) {
        if (turnNo <= 6) {
            return Phase.STUDY;
        } else if (turnNo <= 8) {
            return Phase.RECESS;
        } else if (turnNo <= 14) {
            return Phase.STUDY;
        } else if (turnNo <= 16) {
            return Phase.EXAM;
        } else {
            return Phase.RECESS;
        }
    }

    private List<Events> getEventsForTurn(Phase currentPhase, int turnNo) {
        List<Events> phaseEvents = switch (currentPhase) {
            case STUDY -> studyEvents;
            case RECESS -> holidayEvents;
            case EXAM -> examEvents;
        };

        return new ArrayList<>(phaseEvents.stream()
                .filter(e -> e.turn() == turnNo)
                .filter(e -> e.requiredProjectRoute() == null
                        || e.requiredProjectRoute() == storyState.getProjectRoute())
                .toList());
    }

    public void mainGameFlow(Character character) {
        final int TOTAL_TURNS = 20;
        final int MAX_ENERGY = 10;

        Phase previousPhase = null;

        for (int currentTurn = 1; currentTurn <= TOTAL_TURNS; currentTurn++) {
            Phase currentPhase = getPhase(currentTurn);
            UI.printPhaseTransition(previousPhase, currentPhase);
            previousPhase = currentPhase;

            List<Events> eventsThisTurn = getEventsForTurn(currentPhase, currentTurn);

            if (eventsThisTurn.isEmpty()) {
                System.out.println("No main events found for turn " + currentTurn + ". Skipping...");
                continue;
            }

            try {
                turnProcessor.playTurn(
                        character,
                        currentTurn,
                        MAX_ENERGY,
                        eventsThisTurn,
                        currentPhase,
                        randomEvents,
                        storyState
                );
            } catch (Exception e) {
                System.out.println("An error occurred during turn " + currentTurn + ". Skipping...");
            }
        }

        if (previousPhase != null) {
            System.out.println("\n=== " + previousPhase.getDisplayName() + " Completed! ===");
        }
    }

    public StoryState getStoryState() {
        return storyState;
    }
}
