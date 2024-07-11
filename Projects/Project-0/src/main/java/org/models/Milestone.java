package org.models;

public class Milestone {
    private int milestone_id;

    private String milestone_name;

    private String milestone_description;

    public Milestone(int milestone_id, String milestone_name, String milestone_description) {
        this.milestone_id = milestone_id;
        this.milestone_name = milestone_name;
        this.milestone_description = milestone_description;
    }

    public Milestone(){}

    public int getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(int milestone_id) {
        this.milestone_id = milestone_id;
    }

    public String getMilestone_name() {
        return milestone_name;
    }

    public void setMilestone_name(String milestone_name) {
        this.milestone_name = milestone_name;
    }

    public String getMilestone_description() {
        return milestone_description;
    }

    public void setMilestone_description(String milestone_description) {
        this.milestone_description = milestone_description;
    }
}
