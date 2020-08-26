package com.iview.testclient.taskdata;

public class TaskData {
    TaskPoint startPoint;
    InterPoint interPoint;
    TaskPoint stopPoint;
    MoveSetting moveSetting;

    public TaskData(TaskPoint startPoint, InterPoint interPoint, TaskPoint stopPoint, MoveSetting moveSetting) {
        this.startPoint = startPoint;
        this.interPoint = interPoint;
        this.stopPoint = stopPoint;
        this.moveSetting = moveSetting;
    }


    public TaskPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(TaskPoint startPoint) {
        this.startPoint = startPoint;
    }

    public InterPoint getInterPoint() {
        return interPoint;
    }

    public void setInterPoint(InterPoint interPoint) {
        this.interPoint = interPoint;
    }

    public TaskPoint getStopPoint() {
        return stopPoint;
    }

    public void setStopPoint(TaskPoint stopPoint) {
        this.stopPoint = stopPoint;
    }


    public MoveSetting getMoveSetting() {
        return moveSetting;
    }

    public void setMoveSetting(MoveSetting moveSetting) {
        this.moveSetting = moveSetting;
    }

    @Override
    public String toString() {
        return "TaskData{" +
                "startPoint=" + startPoint +
                ", interPoint=" + interPoint +
                ", stopPoint=" + stopPoint +
                ", moveSetting=" + moveSetting +
                '}';
    }

}
