package domain;


import java.util.ArrayList;

public class SuspiciousUserBuilder {
    private String ip;
    private int frequency;
    private ArrayList<String> timestamps;

    public String getIp() {
        return ip;
    }

    public int getFrequency() {
        return frequency;
    }

    public ArrayList<String> getTimestamps() {
        return timestamps;
    }

    public SuspiciousUserBuilder withIP(String ip) {
        this.ip = ip;
        return this;
    }

    public SuspiciousUserBuilder withFrequency(int frequency) {
        this.frequency = frequency;
        return this;
    }

    public SuspiciousUserBuilder withTimestamps(ArrayList<String> timestamps) {
        this.timestamps = timestamps;
        return this;
    }

    public SuspiciousUser build() {
        return new SuspiciousUser(this);
    }

}
