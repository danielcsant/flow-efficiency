package flowefficiency;

public class UserStory {

    private String key;
    private String summary;
    private float timeSpent;
    private long cycleTime;
    private float flowEfficiency;
    private String epicName;

    public UserStory(String key, String summary, float timeSpent, long cycleTime, float flowEfficiency) {
        this.key = key;
        this.summary = summary;
        this.timeSpent = timeSpent;
        this.cycleTime = cycleTime;
        this.flowEfficiency = flowEfficiency;
    }

    public String getKey() {
        return key;
    }

    public String getSummary() {
        return summary;
    }

    public float getTimeSpent() {
        return timeSpent;
    }

    public long getCycleTime() {
        return cycleTime;
    }

    public float getFlowEfficiency() {
        return flowEfficiency;
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "key='" + key + '\'' +
                ", summary='" + summary + '\'' +
                ", timeSpent=" + timeSpent +
                ", cycleTime=" + cycleTime +
                ", flowEfficiency=" + flowEfficiency +
                '}';
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;
    }

    public String getEpicName() {
        return epicName;
    }
}
