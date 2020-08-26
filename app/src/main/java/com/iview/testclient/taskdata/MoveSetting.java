package com.iview.testclient.taskdata;

import java.util.List;

public class MoveSetting {
    String speed;
    Display display;

    public MoveSetting(String speed, Display display) {
        this.speed = speed;
        this.display = display;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "MoveSetting{" +
                "speed='" + speed + '\'' +
                ", display=" + display +
                '}';
    }

    class Display {
        String type;
        List<String> filelist;
        float initAngle;
        String annimation;
        boolean followMotor;

        public Display(String type, List<String> filelist, float initAngle, String annimation, boolean followMotor) {
            this.type = type;
            this.filelist = filelist;
            this.initAngle = initAngle;
            this.annimation = annimation;
            this.followMotor = followMotor;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getFilelist() {
            return filelist;
        }

        public void setFilelist(List<String> filelist) {
            this.filelist = filelist;
        }

        public float getInitAngle() {
            return initAngle;
        }

        public void setInitAngle(float initAngle) {
            this.initAngle = initAngle;
        }

        public String getAnnimation() {
            return annimation;
        }

        public void setAnnimation(String annimation) {
            this.annimation = annimation;
        }

        public boolean isFollowMotor() {
            return followMotor;
        }

        public void setFollowMotor(boolean followMotor) {
            this.followMotor = followMotor;
        }

        @Override
        public String toString() {
            return "Display{" +
                    "type='" + type + '\'' +
                    ", filelist=" + filelist +
                    ", initAngle=" + initAngle +
                    ", annimation='" + annimation + '\'' +
                    ", followMotor=" + followMotor +
                    '}';
        }
    }
}
