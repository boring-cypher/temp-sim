package sims.event;

public class StoryState {

    public enum ProjectRoute {
        UNSET,
        CARRY,
        REPAIR,
        ESCALATE
    }

    private ProjectRoute projectRoute = ProjectRoute.UNSET;

    public ProjectRoute getProjectRoute() {
        return projectRoute;
    }

    public void setProjectRoute(ProjectRoute projectRoute) {
        this.projectRoute = projectRoute;
    }
}