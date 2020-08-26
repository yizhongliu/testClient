package com.iview.testclient;

public class SocketMsg<T> {
    String type;
    String action;
    T args;

    public SocketMsg(String type, String action, T args) {
        this.type = type;
        this.action = action;
        this.args = args;
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

    public T getArgs() {
        return args;
    }

    public void setArgs(T args) {
        this.args = args;
    }

    @Override
    public String toString() {
        String argString = null;
        if (args != null) {
            argString = args.toString();
        } else {
            argString = "args is null";
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("type :").append(type).append(",action:").append(action).append(",args:").append(argString);
        return stringBuffer.toString();
    }
}
