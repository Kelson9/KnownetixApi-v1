package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

import lombok.Data;

@Data
public class FuzzyNumber {

    private double[] membershipFunction;

    public double getMembershipDegree(double x) {
        // Implement the membership function logic
        return membershipFunction[7];
    }
}
