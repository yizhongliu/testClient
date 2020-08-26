package com.iview.testclient.taskdata;

public class TaskPoint {
    Location location;
    Display display;

    public TaskPoint(Location location, Display display) {
        this.location = location;
        this.display = display;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "TaskPoint{" +
                "location=" + location +
                ", display=" + display +
                '}';
    }
}
