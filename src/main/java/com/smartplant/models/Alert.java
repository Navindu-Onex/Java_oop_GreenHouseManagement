package com.smartplant.models;

public class Alert {
    private final String id;
    private final String time;
    private final String source;
    private final String description;
    private final String severity;
    private String status;

    public Alert(String id, String time, String source, String description, String severity, String status) {
        this.id=id; this.time=time; this.source=source; this.description=description;
        this.severity=severity; this.status=status;
    }
    public String getId() { return id; }
    public String getTime() { return time; }
    public String getSource() { return source; }
    public String getDescription() { return description; }
    public String getSeverity() { return severity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status=status; }
}
