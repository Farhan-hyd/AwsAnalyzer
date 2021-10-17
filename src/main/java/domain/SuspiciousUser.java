package domain;


import java.util.ArrayList;

public class SuspiciousUser {
    private final String ip;
    private int frequency;
    private final ArrayList<String> timestamps;
    private String city;
    private String country;
    private String postal;
    private String subdivision;

    public SuspiciousUser(SuspiciousUserBuilder suspiciousUserBuilder){
        ip = suspiciousUserBuilder.getIp();
        frequency = suspiciousUserBuilder.getFrequency();
        timestamps = suspiciousUserBuilder.getTimestamps();
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getIp() {
        return ip;
    }

    public int getFrequency() {
        if (ip == null) {
            return 0;
        }
        return frequency;
    }

    public ArrayList<String> getTimestamps() {
        return timestamps;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostal() {
        return postal;
    }

    public String getSubdivision() {
        return subdivision;
    }
}
