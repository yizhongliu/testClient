package com.iview.testclient;

public class DataTemplate<T> {
    String type;
    String action;
    T arg;

    public DataTemplate(String type, String action, T args) {
        this.type = type;
        this.action = action;
        this.arg = arg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T getArg() {
        return arg;
    }

    public void setArg(T args) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        String argString = null;
        if (arg != null) {
            argString = arg.toString();
        } else {
            argString = "args is null";
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("type :").append(type).append(",action:").append(action).append(",args:").append(argString);
        return stringBuffer.toString();
    }
}
