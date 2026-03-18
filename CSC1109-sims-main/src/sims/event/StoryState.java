package sims.event;

public class StoryState {

    public enum ProjectRoute {
        UNSET,
        CARRY,
        REPAIR,
        ESCALATE
    }

    public ProjectRoute projectRoute = ProjectRoute.UNSET;

    public ProjectRoute getProjectRoute() {
        return projectRoute;
    }

    public void setProjectRoute(ProjectRoute projectRoute) {
        this.projectRoute = projectRoute;
    }
}
