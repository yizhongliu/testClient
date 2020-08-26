package com.iview.testclient.taskdata;

import java.util.List;

public class InterPoint {
    int interPointSize;
    List<InterPointItem> interPointList;

    public InterPoint(int interPointSize, List<InterPointItem> interPointList) {
        this.interPointSize = interPointSize;
        this.interPointList = interPointList;
    }

    public int getInterPointSize() {
        return interPointSize;
    }

    public void setInterPointSize(int interPointSize) {
        this.interPointSize = interPointSize;
    }

    public List<InterPointItem> getInterPointList() {
        return interPointList;
    }

    public void setInterPointList(List<InterPointItem> interPointList) {
        this.interPointList = interPointList;
    }

    public class InterPointItem {
        int index;
        Location location;
        Display display;
        float nextRotate;

        public InterPointItem(int index, Location location, Display display, float nextRotate) {
            this.index = index;
            this.location = location;
            this.display = display;
            this.nextRotate = nextRotate;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
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

        public float getNextRotate() {
            return nextRotate;
        }

        public void setNextRotate(float nextRotate) {
            this.nextRotate = nextRotate;
        }

        @Override
        public String toString() {
            return "InterPointItem{" +
                    "index=" + index +
                    ", location=" + location +
                    ", display=" + display +
                    ", nextRotate=" + nextRotate +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "InterPoint{" +
                "interPointSize=" + interPointSize +
                ", interPointList=" + interPointList +
                '}';
    }
}
