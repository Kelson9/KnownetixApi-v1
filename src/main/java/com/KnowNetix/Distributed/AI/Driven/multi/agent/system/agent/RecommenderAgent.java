package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.QTable;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecommenderAgent extends Agent {
    private UserRepository userRepository;
    private QTable qTable;

    protected void setup() {
        System.out.println("Recommender Agent is ready.");

        // Add behaviors to the agent
        addBehaviour(new MessageReceiver());
        qTable = new QTable();
    }

    public void recommendLearningPath(Long userId) {
        // Retrieve the user from the database
        Optional<User> user = userRepository.findById(userId);

        // Calculate the overall adaptive learning effectiveness E(x, t)
        double adaptiveEffectiveness = calculateAdaptiveEffectiveness(user.get());

        // Use the Q-Learning algorithm to recommend the optimal learning path
       // LearningResource recommendedResource = qTable.getBestAction(user.getCognitiveState());

        // Provide the recommended learning resource to the student
        //
    }

    private double calculateAdaptiveEffectiveness(User user) {
       // FuzzyNumber cognitiveState = user.getCognitiveState();
       // FuzzyNumber learningPreferences = user.getLearningPreferences();
       // FuzzyNumber forgettingFunction = new FuzzyNumber(/* membership function data */);

//        double cognitiveStateDegree = cognitiveState.getMembershipDegree(0.8);
//        double learningPreferenceDegree = learningPreferences.getMembershipDegree(0.8);
//        double forgettingDegree = forgettingFunction.getMembershipDegree(0.8);

//        return cognitiveStateDegree * learningPreferenceDegree * forgettingDegree;
        return 0.5;
    }

    protected void takeDown() {
        System.out.println("Recommender Agent is shutting down.");
    }

    private class MessageReceiver extends CyclicBehaviour {
        public void action() {
            ACLMessage message = receive();
            if (message != null) {
                String content = message.getContent();
                System.out.println("Recommender Agent received message: " + content);
            } else {
                block();
            }
        }
    }

    public void sendMessageToAssessmentAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("assessmentAgent", AID.ISLOCALNAME));
        send(message);
    }

    public void sendMessageToUserAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("userAgent", AID.ISLOCALNAME));
        send(message);
    }

    public void sendMessageToTutoringAgentAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("tutoringAgent", AID.ISLOCALNAME));
        send(message);
    }

    public void sendMessageToResourceManagementAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("resourceManagementAgent", AID.ISLOCALNAME));
        send(message);
    }
}