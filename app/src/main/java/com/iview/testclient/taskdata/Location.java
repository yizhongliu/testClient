package com.iview.testclient.taskdata;

public class Location {
    public int xStep;
    public int yStep;

    public Location(int xStep, int yStep) {
        this.xStep = xStep;
        this.yStep = yStep;
    }

    public int getxStep() {
        return xStep;
    }

    public void setxStep(int xStep) {
        this.xStep = xStep;
    }

    public int getyStep() {
        return yStep;
    }

    public void setyStep(int yStep) {
        this.yStep = yStep;
    }

    @Override
    public String toString() {
        return "Location{" +
                "xStep=" + xStep +
                ", yStep=" + yStep +
                '}';
    }
}
