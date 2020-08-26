package com.iview.testclient.taskdata;

public class Display {
    String type;
    String fileName;
    float initAngle;
    int showTime;
    String animation;

    public Display(String type, String fileName, float initAngle, int showTime, String animation) {
        this.type = type;
        this.fileName = fileName;
        this.initAngle = initAngle;
        this.showTime = showTime;
        this.animation = animation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public float getInitAngle() {
        return initAngle;
    }

    public void setInitAngle(float initAngle) {
        this.initAngle = initAngle;
    }

    public int getShowTime() {
        return showTime;
    }

    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    @Override
    public String toString() {
        return "Display{" +
                "type='" + type + '\'' +
                ", fileName='" + fileName + '\'' +
                ", initAngle=" + initAngle +
                ", showTime=" + showTime +
                ", animation='" + animation + '\'' +
                '}';
    }
}
