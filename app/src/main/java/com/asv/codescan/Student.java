package com.asv.codescan;

public class Student {
    private String studId;
    private String studName;
    private String teamName;
    private String studColg;
    private String studTeez;
    private boolean visited;

    public Student(String studId, String studName, String teamName, String studColg, String studTeez, boolean visited) {
        this.studId = studId;
        this.studName = studName;
        this.teamName = teamName;
        this.studColg = studColg;
        this.studTeez = studTeez;
        this.visited = visited;
    }

    public String getStudId() {
        return studId;
    }

    public String getStudName() {
        return studName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getStudColg() {
        return studColg;
    }

    public String getStudTeez() {
        return studTeez;
    }

    public boolean isVisited() {
        return visited;
    }
}
