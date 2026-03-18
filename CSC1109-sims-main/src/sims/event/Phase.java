package sims.event;

import java.util.List;

public enum Phase{
    STUDY("Study Phase"),
    RECESS("Recess Phase"),
    EXAM("Exam Phase");

    private final String displayName;

    Phase(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Events> buildEvents(){
        switch (this){
            case STUDY:
                return StudyWeekEventBuilder.buildStudyEvents();
            case RECESS:
                return HolidayEventBuilder.buildHolidayEvents();
            case EXAM:
                return ExamWeekBuilder.buildExamEvents();
            default:
                throw new IllegalStateException("Unexpected phase");
        }
    }
}