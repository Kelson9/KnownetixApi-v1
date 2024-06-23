package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Recommendation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LearningPathRecommender {

    private double[][] qTable = {
            {5, 4, 3, 2, 1, 0},
            {4, 5, 3, 2, 1, 0},
            {4, 3, 5, 2, 1, 0},
            {4, 3, 2, 5, 1, 0},
            {4, 3, 2, 1, 5, 0},
            {4, 3, 2, 1, 0, 5}
    };

    private final double GAMMA = 0.9;
    private final double ALPHA = 0.1;

    public void qLearning(int state, int action, double reward, int nextState) {
        qTable[state][action] = qTable[state][action] + ALPHA * (reward + GAMMA * getMaxQValue(nextState) - qTable[state][action]);
    }

    public Recommendation recommendLearningPath(int userState) {
        int recommendedAction = getMaxQValueIndex(userState);
        List<String> learningMaterials = Arrays.asList(
                "Introduction to the topic",
                "Intermediate-level tutorial",
                "Advanced topic analysis",
                "Hands-on project",
                "Supplementary materials",
                "Assessment and feedback"
        );

        return new Recommendation(recommendedAction, learningMaterials.get(recommendedAction));
    }

    private int getMaxQValueIndex(int state) {
        double maxValue = Double.MIN_VALUE;
        int maxIndex = -1;

        for (int i = 0; i < qTable[state].length; i++) {
            if (qTable[state][i] > maxValue) {
                maxValue = qTable[state][i];
                maxIndex = i;
            }
        }

        if (maxIndex == -1) {
            // If no positive value was found, return a default value
            return 0;
        }

        return maxIndex;
    }

    private double getMaxQValue(int state) {
        double maxValue = Double.MIN_VALUE;

        for (double qValue : qTable[state]) {
            if (qValue > maxValue) {
                maxValue = qValue;
            }
        }

        return maxValue;
    }

    public double[][] getQTable() {
        return qTable;
    }
}