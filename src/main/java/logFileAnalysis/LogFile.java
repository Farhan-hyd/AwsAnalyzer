package logFileAnalysis;


import java.util.Objects;

public class LogFile {
    private final String name;
    private long size;
    private float increaseInSize;

    public LogFile(String name) {
        this.name = name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public void setIncreaseInSize(float increaseInSize) {
        this.increaseInSize = increaseInSize;
    }

    public float getIncreaseInSize() {
        return increaseInSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogFile logFile = (LogFile) o;
        return Objects.equals(name, logFile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
