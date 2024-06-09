package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

public class Recommendation {
    private int action;
    private String learningMaterials;

    public Recommendation(int action, String learningMaterials) {
        this.action = action;
        this.learningMaterials = learningMaterials;
    }

    public int getAction() {
        return action;
    }

    public String getLearningMaterials() {
        return learningMaterials;
    }
}